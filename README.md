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
diakses dengan 'http://localhost:9057/customers/1'

# 3. GET_customer_{id}_cards
**Mendapatkan Informasi Kartu Pelanggan berdasarkan ID**
![3 GET _customers_{id}_cards](https://github.com/gusaditt/Tugas-1-PBO/assets/147296239/c0219ec7-43a2-4715-a013-e5774191b5e1)
diakses dengan 'http://localhost:9057/customers/1/cards'

# 4. GET_customer_{id}_subscriptions
**Mendapatkan Informasi Mengenai daftar Subscriptions Milik Pelanggan**
![4 GET _customers_{id}_subscriptions](https://github.com/gusaditt/Tugas-1-PBO/assets/147296239/93c15642-48f1-4d3d-be87-45867a133a2d)
diakses dengan 'http://localhost:9057/customers/3/subscriptions'

# 6. GET_subscriptions
**Mendapatkan Data dari Semua Subscriptions**
![6 GET _subscriptions](https://github.com/gusaditt/Tugas-1-PBO/assets/147296239/716f8180-d299-4e0a-88cf-fcd485185cb8)
diakses dengan 'http://localhost:9057/subscriptions'

# 9. GET_items
**Mendapatkan Data dari Semua Items**
![9 GET _items](https://github.com/gusaditt/Tugas-1-PBO/assets/147296239/cb3e56bd-f6f6-4cab-bf71-cc0e70adea9c)
diakses dengan 'http://localhost:9057/items'

# 11. GET_items_{id}
**Mendapatkan Data dari Items berdasarkan ID**
![11 GET _items_{id}](https://github.com/gusaditt/Tugas-1-PBO/assets/147296239/d64f09aa-2d4b-47c1-92e9-d006a3ba7029)
diakses dengan 'http://localhost:9057/items/1'

# 12. POST_customers
**Membuat Data Baru pada Customers**
![12 POST _customers](https://github.com/gusaditt/Tugas-1-PBO/assets/147296239/58b24e96-cdf3-48ac-a33a-8f91bf196125)
diakses dengan 'http://localhost:9057/customers'

# 14. POST_items
**Membuat Data Baru pada Items**
![14 POST _items](https://github.com/gusaditt/Tugas-1-PBO/assets/147296239/5ed1ef27-1ca7-46d9-9a5c-460bfcda7e2f)
diakses dengan 'http://localhost:9057/items'

# 15. PUT_customers_{id}
**Merubah Data pada Customer**
![15 PUT _customers_{id}](https://github.com/gusaditt/Tugas-1-PBO/assets/147296239/e743d610-8bb5-42ce-923b-77fc080a207f)
diakses dengan 'http://localhost:9057/customers/1'

# 17. PUT_items_{id}
**Merubah Data pada Items**
![17 PUT _items_{id}](https://github.com/gusaditt/Tugas-1-PBO/assets/147296239/40c831d5-7e11-4a84-a9bd-b0449163da89)
diakses dengan 'http://localhost:9057/items/7'

# 18. DELETE_items{id}
![18 DELETE _items_{id}](https://github.com/gusaditt/Tugas-1-PBO/assets/147296239/0cc38a24-8892-4db3-abd5-339f7490bff4)
diakses dengan 'http://localhost:9057/items/2'

# 19. DELETE_customers_{id}_cards_{id}
![19 DELETE _customers_{id}_cards_{id}](https://github.com/gusaditt/Tugas-1-PBO/assets/147296239/1fe378e4-7d7a-4365-a4b9-e8890e223407)
diakses dengan 'http://localhost:9057/customers/4/cards/4'
