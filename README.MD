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
    @Enumerated(EnumType.STRING)  
    private EAppointmentType appointmentType;  
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


