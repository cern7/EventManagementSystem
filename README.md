# EventManagementSystem
Event Management System based on MVC-structure using SQLite as a database+

> Project Description

Creating a Java Desktop application to manage various events. The application
collects the followind details:
* Title and description of the event
* Type of the event (online or physical)
* Address of the event
* Date of the event
* Place limitations
* Event organiser

Users are able to register on the system so they can book or post events.

To register, they need to provide username, first name, last name and email
address. Once registered users need to login to use the system.

Users are able to view all events and to make a booking. Also they are able to
view and cancel their own bookings.

The application is using the SQLite database to store user information, event
details as well as booking details.


> Design

**Database Entities:**
* Bookings(**TicketNo**, bookingTime)
* Events(**EventID**, EventName, EventType, AddressURL, startDate, endDate, Description, PlaceLimitations)
* Passwords(**ID**, Hash1, Salt2)
* UserInfo(**ID**, FirstName, LastName, EmailAddress)
* Users(**UserID**, Username, Salt1, Hash2)


**Skeleton Tables**
* Bookings(**TicketNo**, Guest*, Event*, bookingTime)
* Events(**EventID**, EventName, EventType, AddressURL, startDate, endDate, Description, PlaceLimitations, Organiser*) 
* Passwords(**ID**, Hash1, Salt2)
* UserInfo(**ID**\*, FirstName, LastName, EmailAddress)
* Users(**UserID**, Username, Salt1, Hash2)

## 
- Primary key = **Bold**
- Foreign Key = *


#### Table Definition
* *Bookings*

| Field Name        | Datatype       |  Length |Index | Null  |  Default        |
| :---------------: | :------------: |:-------:|:---: | :---: |:-------------:  | 
| TicketNo          |  INTEGER       |         |  PK  |NO     |                 |
|  Guest            | INTEGER        |         |  FK  |  NO   |                 |
|  Event            | INTEGER        |         |  FK  |NO     |                 |
|  bookingTime      | DATETIME       |         |      | No    |CURRENT_TIMESTAMP|

* *Events*

| Field Name        | Datatype       |  Length |Index | Null  |  Default        |
| :---------------: | :------------: |:-------:|:---: | :---: |:-------------:  | 
| EventID           |  INTEGER       |         |  PK  |NO     |                 |
|  EventName        | VARCHAR        |  25     |      |  NO   |                 |
|  EventType        | VARCHAR        |   8     |      |NO     |                 |
|  AddressURL       | VARCHAR        |  2000   |      | NO    |                 |
|  startDate        | DATETIME       |         |      |  NO   |                 |
|  endDate          | DATETIME       |         |      |NO     |                 |
|  Description      | VARCHAR        |  2000   |      | NO    |                 |
|  PlaceLimitations | REAL           |         |      |NO     |                 |
|  Organiser        |                |         | FK   | NO    |                 |


* *Passwords*

| Field Name        | Datatype       |  Length |Index | Null  |  Default        |
| :---------------: | :------------: |:-------:|:---: | :---: |:-------------:  | 
| ID                |  INTEGER       |         |  PK  |NO     |                 |
|  Hash1            | VARCHAR        |   128   |      |  NO   |                 |
|  Salt2            | BLOB           |         |      |NO     |                 |


* *UserInfo*

| Field Name        | Datatype       |  Length |Index | Null  |  Default        |
| :---------------: | :------------: |:-------:|:---: | :---: |:-------------:  | 
| ID                |  INTEGER       |         |PK FK |NO     |                 |
|  FirstName        | VARCHAR        | 40      |      |  NO   |                 |
|  LastName         | VARCHAR        | 40      |      |NO     |                 |
|  EmailAddress     | VARCHAR        | 40      |      | NO    |                 |


* *Users*

| Field Name        | Datatype       |  Length |Index | Null  |  Default        |
| :---------------: | :------------: |:-------:|:---: | :---: |:-------------:  | 
| UserID            |  INTEGER       |         |  PK  |NO     |                 |
|  Username         | VARCHAR        | 48      |      |  NO   |                 |
|  Salt1            | BLOB           |         |      |NO     |                 |
|  Hash2            | VARCHAR        |  128    |      | NO    |                 |




Application was designed based on MVC architecture in order to separate the application logic from the user interface. The View layer is represented by the
classes that communicate  with the user over the terminal. The Model layer comprises the ADTs definition and classes that work with these
ADTs.
The Controller layer is an agent that acts by invoking the Model layer and
sending the retrieved data to the View layer.

**Login System**

I chose to use a technique called [Security Through Obesity](https://opine.me/a-better-way-to-store-password-hashes/), mentioned [here](https://thezaz.com/blog/storing_passwords_securely_wit), as a base for application login system.Basiclly it splits the user credentials in two database tables that are not related.

The first table, *Users* table, stores the *Username*, *Salt1* and *Hash2*. All three columns are unique. The *Passwords* table holds *Hash1* and *Salt2* columns

First thing the system is doing in the user registration process is checking the username availability. Then the system creats two Salts using [**SecureRandom**](https://docs.oracle.com/javase/8/docs/api/java/security/SecureRandom.html#getInstanceStrong--) class that provides a cryptographically strong number generator(RNG). By default is's configured to use blocking algorithm.
```
private byte[] saltSpawn() {
        byte[] salt = new byte[64];
        try {
                SecureRandom secRan = SecureRandom.getInstanceStrong();
                secRan.nextBytes(salt);
        } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
        }
        return salt;
}
```

Finally the system generates two hashes from these salts. The hash generation method is using the [MessageDigest](https://docs.oracle.com/javase/8/docs/api/java/security/MessageDigest.html) class functionality  with the SHA-256 as a message digest algorithm. Before passing the data to the database, the hashes are converted to hexadecimal format using [StringBuilder](https://docs.oracle.com/javase/7/docs/api/java/lang/StringBuilder.html) class.

```
private String hashGenerate(byte[] salt, String password) {
        // create MessageDigest instance of SHA-256
        String saltString = "";
    String hash = "";
        for (int i = 0; i < salt.length; i++) {
                saltString += salt[i];
        }
        try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                messageDigest.update(salt);
                byte[] bytes = messageDigest.digest((password + saltString).getBytes());
                // convert bytes[] to hexadecimal format
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < bytes.length; i++) {
                        stringBuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }
                hash = stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        return hash;
        }
```

**User Validation**

After collecting the username and password from the user, the system will query *Salt1* and *Hash2* from the *Users* table. Using *Salt1* and user password it will compute the *Hash1*. Then it will query the *Passwords* table for computed *Hash1*. If found, the system will compute the *Hash2* using the user password and the matching *Salt2* from the *Passwords* table. If the computed *Hash2* mathces the *Hash2* stored in the database, user is validated.
