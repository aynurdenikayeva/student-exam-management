# Student Exam Management System

## Layihənin məqsədi
Bu sistem birinci imtahandan uğurla keçmiş tələbələrin ikinci mərhələyə qeydiyyatını təmin edir. Sistem aşağıdakı funksionallıqları təmin edir:

- Tələbələrin məlumat bazasına əlavə olunması
- Birinci imtahandan keçən tələbələrin ikinci imtahana qeydiyyatı
- QR Code ilə ikinci imtahana girişin təmin edilməsi
- QR Code-un email vasitəsilə tələbəyə göndərilməsi
- Password-ların təhlükəsiz şəkildə hash-lənməsi
- HTTP status kodlarına uyğun API response-lar
- İstifadəçi artıq QR kodu varsa, onun təkrar yaradılmaması
- Aiven PostgreSQL serverinə qoşulma və manual test məlumatları ilə işləmə

---

## Texnologiyalar

- Java 17+
- Spring Boot
- Spring Data JPA
- PostgreSQL (Aiven server)
- Lombok
- Spring Mail
- ZXing (QR Code generation)
- Maven
- Git

---

## Database Structure

### Student
| Field    | Type         | Constraints          |
|----------|-------------|--------------------|
| id       | BIGINT      | PK, Auto-increment |
| name     | VARCHAR     | Not null           |
| surname  | VARCHAR     | Not null           |
| email    | VARCHAR     | Not null, Unique   |
| password | VARCHAR     | Not null           |

### First_exam
| Field                | Type     | Constraints          |
|----------------------|---------|--------------------|
| id                   | BIGINT  | PK, Auto-increment |
| student_id           | BIGINT  | FK → Student(id)   |
| result               | INTEGER | Nullable           |
| pass                 | BOOLEAN | Not null           |
| second_exam_qr_url    | VARCHAR | Nullable           |

### Second_exam
| Field       | Type     | Constraints          |
|-------------|---------|--------------------|
| id          | BIGINT  | PK, Auto-increment |
| student_id  | BIGINT  | FK → Student(id)   |
| result      | INTEGER | Nullable           |
| pass        | BOOLEAN | Nullable           |

---

## API Endpoints

### 1. `POST /student/register`
Tələbənin sistemə qeydiyyatı üçün endpoint.
### 2. `POST /secondExam/register`
İkinci imtahana qeydiyyat üçün endpoint.

**Request body:**
```json
{
  "name": "Aynur",
  "surname": "Denikayeva",
  "email": "student@example.com",
  "password": "securepassword"
}

---

```json
{
  "email": "student@example.com",
  "password": "securepassword"
}
