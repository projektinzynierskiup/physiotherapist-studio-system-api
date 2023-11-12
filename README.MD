### Role na serwerze
Role są zbudowane w hierarchii odwróconej piramidy.  
`1.`ADMIN  
`2.`MOD  
`3.`GUEST  
`4.`USER

---

### Adres API  
**localhost:8080**

---

### Proces autorzyacjny  
W przypadku, gdy osoba została uwierzytelniona i ma już token JWT w cookies podczas
wysyłania zapytania na endpoint, który jest jej umożliwiony dzięki roli na 
serwerze token ten jest walidowany przez *JwtAuthenticationFilter* gdzie jest
implementacja walidatora udostępniona przez pakiet *io.jsonwebtoken*. Filtr jest wywoływany
przy każdym zapytaniu do API.  
Jeżeli użytkownik wyśle zapytanie na endpoint do którego nie ma dostępu otrzymuje *Status code 401*

---

### Endpoint: Massage - /guest/massage

**GET /all**  
Status code: 400  
Return: List of MassageDTO  
Atrybuty MassageDTO:  
````
public class MassageDTO {  
    private Integer id;  
    private String massageName;  
    private String description;  
    private String appointmentType;  
}
````

**Przykładowy JSON**  
```json
[{
"id": 1,
"massageName": "Relaksacyjny Masaz",
"description": "Masaz dla relaksu",
"appointmentType": "RELAKSACYJNY"
},
{
"id": 2,
"massageName": "Terapeutyczny Masaz",
"description": "Masaz terapeutyczny",
"appointmentType": "TERAPEUTYCZNY"
},
{
"id": 3,
"massageName": "Masaz Glowy",
"description": "Masaz dla glowy",
"appointmentType": "GŁOWY"}
]
```
---
### Endpointy uwierzytelniajace: /guest
**POST /register**
````
public class UsersDTO {  
private String email;  
private String username;  
private String surname;  
private String password; 
private String phone;
private boolean enabled;
````

Warunki:  
email min. 5 znaków   
username 3<=length<=25  
surname 3<=length<=25  
password 8<=length<=31

email - format emailu ````*@*.*````  
password - hasło zawierające przynajmniej jedną dużą literę. Dozwolone są znaki specjalne oraz cyfry. Bez białych znaków. Hasło jest hashowane za pomocą BCrypt  

*Status code: 200*  
*Return: info*
```json
{
    "info": "Zarejestrowano"
}
```
- 

*Status code 400*  
*return: info*  
W przypadku niepoprawnych danych użytkownik nie jest uwierzytelniany.
```json
{
  "info": "Użytkownik o takim emailu jest już w bazie"
}
```

**POST /login**
````
public class UserCredentialsDTO {
    private String email;
    private String password;
}
````
Warunki:  
email min. 5 znaków   
password 8<=length<=31  

*Status code 200*  
*return: token*
```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTYW1wbGV1c2VyIiwicm9sZSI6Ik1PRCIsImVtYWlsIjoibWF0aUB6YWsucGwiLCJpZCI6NCwidXNlcm5hbWUiOiJTYW1wbGV1c2VyIiwiaWF0IjoxNjk4Njk0MzMwLCJleHAiOjE2OTkwNTQzMzB9.5WNAkGC8ZAZHpdah5Dwiuu68ikkIfOO7AvaBB6Xkrd4"
}
```

*Status code 400*  
*return: info*
```json
{
    "info": "Wprowadzono nieprawidłowe haslo"
}
```
---

### Endpoint: Calendar - /mod/calendar
**GET /{date}**  
format daty: *YYYY-MM-DD*  
Status code: 200  
return: ```List<CalendarDTO>```
```
public class CalendarDTO {
    private LocalDate localDate;
    private List<UsersDTO> usersDTOList;
}
```
```
public class UsersDTO {
    private Integer id;
    private String email;
    private String username;
    private String surname;
    private String password;
    private LocalTime localTime;
    private MassageDTO massageDTO;
    private String phone;
}
```
```
public class MassageDTO {
    private Integer id;
    private String massageName;
    private String description;
    @Enumerated(EnumType.STRING)
    private EAppointmentType appointmentType;
}
```  
Request zwraca wszystkie umówione wizyty z zakresu *date - date + 7 dni*. JSON zwraca posortowany wg. *date* ASC  
Przykładowy JSON
```json
[{
        "localDate": "2023-10-16",
        "usersDTOList": [
            {
                "id": 1,
                "email": "jan.kowalski@example.com",
                "username": "Jan",
                "surname": "Kowalski",
                "password": "password1",
                "localTime": "10:00:00",
                "massageDTO": {
                    "id": 2,
                    "massageName": "Terapeutyczny Masaz",
                    "description": "Masaz terapeutyczny",
                    "appointmentType": "TERAPEUTYCZNY"
                }
            }
        ]
    },
    {
        "localDate": "2023-10-17",
        "usersDTOList": [
            {
                "id": 2,
                "email": "anna.nowak@example.com",
                "username": "Anna",
                "surname": "Nowak",
                "password": "password2",
                "localTime": "10:00:00",
                "massageDTO": {
                    "id": 1,
                    "massageName": "Relaksacyjny Masaz",
                    "description": "Masaz dla relaksu",
                    "appointmentType": "RELAKSACYJNY"
                }
            }
        ]
    }]
```
----

### Endpoint Appointment - /guest/appointment
```
public class AppointmentDTO {
    private Integer id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer userId;
    private Integer massageId;
    private String status;
}
```

```
public class AppointmentWithEmailDTO {

    private Integer id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer userId;
    private Integer massageId;
    private String status;
    private String userEmail;
    private String userPhone;
}
```

```
public enum EAppointmentStatus {

     FREE, BOOKED, FINISHED
 }
 ```

**GET /guest/appointment/{appointmentId}**

**GET /guest/appointment/free** - bierze wszystkie Appointment ze statusem FREE

**PUT /guest/appointment/{appointmentId}/book** - zmienia status Appointment na BOOKED (dla zalogowanego uytkownika)

Przyjmuję AppointmentDTO

**PUT /guest/appointment/{appointmentId}/book/guest** - zmienia status Appointment na BOOKED (dla uytkowników nie zalogowanych) 

Przyjmuję AppointmentWithEmailDTO

**PUT /guest/appointment/{appointmentId}/cancel** - zmienia status Appointment na FREE

### Endpoint Offer - /guest/offer
```
public class AppointmentDTO {
    private Integer id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer userId;
    private Integer massageId;
    private String status;
}
```

**POST /guest/offer**

**GET /guest/offer/{offerId}**

**GET /guest/offer/all**

**PUT /guest/offer/{offerId}**

**DELETE /guest/offer/{offerId}**

### Endpoint Offer Photo - /guest**
```
public class OfferPhotoDTO {

    private Integer id;
    private String photoName;
    private byte[] photoByte;
    private String photoType;
    private Integer offerId;
}
```

**POST /guest/offer-photo**

**GET /guest/offer-photo/{offerPhotoId}**

**PUT /guest/offer-photo/{offerPhotoId}**

**DELETE /guest//offer-photo/{offerPhotoId}**

### Endpointy dla MODA - /mod**

**POST /mod/appointment** - tworzenie Appointment

**POST /mod/appointment/all-day** - (startDate, endDate) tworzenie listy Appointment w danym dniu

**GET /mod/appointment/{appointmentId}** 

**GET /mod/appointment/all/finished** - zwraca wszystkie Appointment ze statusem FINISHED

**PUT /mod/appointment/{appointmentId}**

**PUT /mod/appointment/{appointmentId}/finished** - zmienia status Appointment na FINISHED

**DELETE /mod/appointment/{appointmentId}**

**DELETE /mod/newsletter** - przyjmuję NewsletterDTO


----

### Endpoint: Email - /mod/email
**POST /confimration**

````
public class Email {
private int visitId;
private String recipientEmail;
private LocalDateTime startTime;
private LocalDateTime endTime;
private String username;
private String eventName;
private String description;
private EEmailStatus emailStatus;
}
````
**recipientEmail** - email klienta  
**startTime** - data i godzina rozpoczęcia wizyty   
**startTime** - data i godzina zakonczenia wizyty  
**username** - imie klienta  
**eventName** - dany masaz  
**description** - opis (domyślny bądź opcjonalny)  
**emailStatus** - String z typem decyzji:  
  
*ACCEPTATION* - wizyta została zaakceptowana i klient otrzymuje email z potwierdzeniem  
*CHANGE* - wizyta została zmieniona i klient otrzymuje email z nowym terminem wizyty  
*REJECTION* - odmowa wizyty, anulowanie jej wraz ze zmianą statusu w bazie, email do klienta z odmowa  
*BAN* - odmowa wizyty, anulowanie jej wraz ze zmianą statusu w bazie, email do klienta z odmowa, zablokowanie
możliwości składania wizyt w systemie pod dany nr telefonu lub email. W przypadku złego *emailStatus*
aplikacja zwraca status code 401

*Status code: 200*  
*return:```InfoDTO```*
```json
{
    "info": "Wykonano"
}
```

*Status code: 401*  
Nic nie zwraca, jedynie status code
---
### Endpoint: Opinion - /[guest, mod]/opinion
**POST** /guest/opinion  
Opinie można dodawać raz na dobę w celu zapobiegania spamu
```
public class OpinionDTO {
    private String username;
    private String description;
    private Integer rate;
}
```  
**username** - nazwa użytkownika pod którą zostanie wyświetlona opinia  
**description** - treść opinii  
**rate** - ocena w skali 1-5  

*Status code: 200*  
*return: InfoDTO*  
```json
{
    "info": "Dziękujemy za dodanie opinii"
}
```  
*Status code: 419*  
*return: InfoDTO*  
```json
{
    "info": "Przepraszamy. Wystąpił błąd."
}
```  
**GET** /guest/opinion/{page}
```
public class Opinion {
    private Integer id;
    private String username;
    private String description;
    private Integer rate;
    private LocalDate localDate;
}
```
*Status code: 200*  
*return: Opinion*
```json
{
    "content": [
        {
            "id": 19,
            "username": "5455555555",
            "description": "5ref",
            "rate": 5,
            "localDate": "2023-11-02"
        },
        {
            "id": 18,
            "username": "1111111",
            "description": "asadsa",
            "rate": 11,
            "localDate": "2023-11-02"
        },
        {
            "id": 17,
            "username": "fdsfds",
            "description": "fsad",
            "rate": 4,
            "localDate": "2023-11-02"
        },
        {
            "id": 16,
            "username": "jkbj",
            "description": "fsa",
            "rate": 3,
            "localDate": "2023-11-02"
        },
        {
            "id": 15,
            "username": "alloha",
            "description": "fassfa",
            "rate": 2,
            "localDate": "2023-11-02"
        }
    ],
    "pageable": {
        "pageNumber": 1,
        "pageSize": 5,
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "offset": 5,
        "paged": true,
        "unpaged": false
    },
    "last": false,
    "totalPages": 5,
    "totalElements": 24,
    "first": false,
    "numberOfElements": 5,
    "size": 5,
    "number": 1,
    "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
    },
    "empty": false
}
```  
**DELETE** /mod/opinion/{id}  
Usuwa opinie z danym ID  

*Status code: 200*  
*reuturn: InfoDTO*  
```json
{
  "info": "Usunięto opinie z ID: "  <id>
}
```
---
### Endpoint: USERS /guest/users  
**POST** /restartpassword/{email}  
Proces weryfikacyjny czy user o takim emailu istnieje i nie ma roli GUEST. Jeżeli user istnieje
jest tworzony obiekt RegisterPassword w celu zapisania nowego żądania resetu hasła. Do USERa jest wysyłane unikalny
link ze zmienną uuid
```
public class RestartPassword {
    private Integer id;
    private String uuid;
    private Integer usersId;
    private LocalDateTime startLocalDateTime;
    private LocalDateTime endLocalDateTime;
}
```  
id - id RestartPassword  
uuid - uniwersalny klucz  
usersId - id usera otrzymany za pomoca emailu  
startLocalDateTime - czas i data utworzenia żądania  
endLocalDateTime - startLocalDateTime + 15 min  

*Status code: 200*  
*return: InfoDTO*  
```json
{
    "info": "W celu zresetowania swojego hasla sprawdz email"
}
```  
*Status code: 400*  
*return: InfoDTO*  
```json
{
    "info": "Nie odnaleziono osoby o takim emailu"
}
```  
  
**GET** /restartpassword/{uuid}  
User wchodzi pod url, który otrzymał w emailu ze zmienną UUID, który został wygenerowany przy POST.
Przy GET uuid jest weryfikowane. Jeżeli żądanie zostanie zautoryzowane uuid zostaje zwracany na front dla PUT  
*Status code: 200*  
*return: InfoDTO*  
```json
{
    "info": "ea83ee37-f521-4ac0-9299-ebaab1d74c3a"
}
```  
*Status code: 400*  
*return: InfoDTO*  
```json
{
    "info": "Wystąpił błąd z uuid"
}
```  

**PUT** /restartpassword  
Otrzymuje obiekt *RestartPasswordDTO*, który jest ostatecznie walidowany oraz odpowiada za aktualizacje hasła
dla USERa
```
public class RestartPasswordDTO {
    private String uuid;
    private String password;
}
```  
uuid - uuid, które zostało zwrócone przy żądaniu GET  
password - hasło, które ma 8-31 znaków bez białych znaków  

*Status code: 200*  
*return: InfoDTO*  
```json
{
    "info": "Zmieniono haslo"
}
```  
*Status code: 400*  
*return: InfoDTO*  
```json
{
    "info": "Wystąpił błąd z uuid"
}
```
---

### Endpoint Newsletter - /guest**

```
public class NewsletterDTO {

     private Integer id;
     private String userEmail;
     private UUID deleteKey;
 }
```

**POST /guest/newsletter** - przyjmuję NewsletterDTO(w zasadzie sam email), wysyła email przy zapisie
Sprawdza czy user jest juz zapisany do newslettera

Przykładowy Json:
```json
{
    "userEmail": "user@gmail.com"
}
```
*Status code: 200*  
*return: InfoDTO*  
```json
{
    "info": "Dodano do newslettera!"
}
```  
*Status code: 400*  
*return: InfoDTO*  
```json
{
    "info": "Użytkownik jest już zapisany do newslettera!"
}
```
**DELETE /guest/{deleteKey}** - usuwanie poprzez kliknięcie w link w emailu


