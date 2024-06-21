## DISUSUN OLEH : 
1. Putu Arya Ekananda Kusuma Negara (2305551057)
2. Ida Bagus Gde Raditya Wedananta (2305551102)
# API JAVA | SUBSCRIPTIONS
Proyek ini adalah sebuah backend API sederhana yang dibangun menggunakan Java dengan Maven. API ini dirancang untuk aplikasi sederhana, memungkinkan akses dan manipulasi data pada setiap entitas dalam database. API ini mendukung operasi GET, POST, PUT, dan DELETE. Respons dari server API menggunakan format **JSON**, dan data disimpan dalam **SQLite**. Pengujian aplikasi dilakukan menggunakan **Postman**.

# STRUKTUR PROGRAM
Proyek ini memiliki **3 FOLDER** atau **STRUKTUR utamanya**.
## main/java
**ardit/com**
digunakan untuk menyimpan segala entitas yang terdapat pada database.
**server**
digunakan untuk menghandle segala sesuatu yang berhubungan dengan keperluan untuk API dan SERVER.
**persistences**
digunakan untuk menghandle secara keperluan di dalam database.

# SPESIFIKASI PROGRAM
**API** ini berfokuskan pada **4 request methods** : 
- **GET**
- **POST**
- **PUT**
- **DELETE**

# PENGGUNAAN PROGRAM
Program ini menggunakan **port 9057** dan diakses di postman melalui **localhost:9057**

# 1. GET_customers
**Mendapatkan Data dari Semua Customers**
![1 GET _customers](https://github.com/gusaditt/Tugas-2-PBO/assets/147296239/25e71a2c-4826-4248-8a40-f71ba770a5b2)
diakses dengan 'http://localhost:9057/customers'

# 2. GET_customers_{id}
**Mendapatkan Data dari Customer berdasarkan ID**
![2 GET _customers_{id}](https://github.com/gusaditt/Tugas-1-PBO/assets/147296239/2eadfa64-1685-46e4-aa37-6e245f4483d4)
diakses dengan '''http://localhost:9057/customers/1'''

