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

# GET_customers
**Mendapatkan Data dari Semua Customers**
![1 GET _customers](https://github.com/gusaditt/Tugas-1-PBO/assets/147296239/92415005-a298-478e-8a15-bf350097096a)
diakses dengan 'http://localhost:9057/customers'

# GET_customers_{id}
**Mendapatkan Data dari Customer berdasarkan ID**
![2 GET _customers_{id}](https://github.com/gusaditt/Tugas-1-PBO/assets/147296239/731aa2ec-9d42-4803-b909-4b121f3c5b48)
diakses dengan 'http://localhost:9057/customers/1'

# GET_customer_{id}_cards
**Mendapatkan Informasi Kartu Pelanggan berdasarkan ID**
![3  GET _customers_{id}_cards](https://github.com/gusaditt/Tugas-1-PBO/assets/147296239/c0ba6897-c963-4f7b-9eba-a06b821a5c70)
diakses dengan 'http://localhost:9057/customers/1/cards'

# GET_customer_{id}_subscriptions
**Mendapatkan Informasi Mengenai daftar Subscriptions Milik Pelanggan**
![4 GET _customers_{id}_subscriptions](https://github.com/gusaditt/Tugas-1-PBO/assets/147296239/eb098ba4-2170-434f-9f09-0d85e13aceed)
diakses dengan 'http://localhost:9057/customers/3/subscriptions'

# GET_customer_{id}_subscriptions?subscriptions_status
**Mendapatkan Informasi Mengenai Subscriptions Status Milik Pelanggan berdasarkan ID Customer**
![5 GET _customers_{id}_subscriptions_subscriptions_status](https://github.com/gusaditt/Tugas-1-PBO/assets/147296239/b2f71f1b-bea2-4cf8-9c89-1f0a2d736a80)
diakses dengan 'http://localhost:9057/customers/1/subscriptions?subscription_status=active'

# GET_subscriptions
**Mendapatkan Data dari Semua Subscriptions**
![6 GET _subscriptions ](https://github.com/gusaditt/Tugas-1-PBO/assets/147296239/e2962f1d-3f43-48f5-b455-5488ac60297b)
diakses dengan 'http://localhost:9057/subscriptions'

# GET_subscriptions?sort_by=current_term_end&sort_type=desc
**Mendapatkan Daftar Subscriptions yang Diurutkan Berdasarkan Tanggal Akhir Periode Berjalan dalam Urutan Menurun**
![8 GET _subscriptions_sort_by=current_term_end sort_type=desc](https://github.com/gusaditt/Tugas-1-PBO/assets/147296239/1ee7d7ce-4f78-4228-a2a9-fa1127598e66)
diakses dengan 'http://localhost:9057/subscriptions?sort_by=current_term_end&sort_type=desc'

# GET_items
**Mendapatkan Data dari Semua Items**
![9 GET _items](https://github.com/gusaditt/Tugas-1-PBO/assets/147296239/ca4b2605-4d50-458d-8766-8cb8146b55a5)
diakses dengan 'http://localhost:9057/items'

# GET /items?is_active=true
**Mendapatkan Daftar Semua Items yang Memiliki Status Aktif (is_active)**
![10 GET _items_is_active=true ](https://github.com/gusaditt/Tugas-1-PBO/assets/147296239/2bd63fa9-4379-4156-9e7d-4ca8ffe8aaa7)
diakses dengan 'http://localhost:9057/items?is_active=true'

# GET_items_{id}
**Mendapatkan Data dari Items berdasarkan ID**
![11 GET _items_{id}](https://github.com/gusaditt/Tugas-1-PBO/assets/147296239/46f7cbea-ffbd-4721-9f32-bd84b1a1a6b3)
diakses dengan 'http://localhost:9057/items/1'

# POST_customers
**Membuat Data Baru pada Customers**
![12 POST _customers](https://github.com/gusaditt/Tugas-1-PBO/assets/147296239/48ce3b49-a1d6-417b-bf62-e25100c73a14)
diakses dengan 'http://localhost:9057/customers'

# POST_subscriptions 
**Membuat Data Baru pada Subscriptions**
![13 POST _subscriptions ](https://github.com/gusaditt/Tugas-1-PBO/assets/147296239/97436f37-a9fb-4c81-b28a-46909a693608)
diakses dengan 'http://localhost:9057/subscriptions'

# POST_items
**Membuat Data Baru pada Items**
![14 POST _items](https://github.com/gusaditt/Tugas-1-PBO/assets/147296239/209cef8b-425f-4a03-8c7d-d22e4f0cc81f)
diakses dengan 'http://localhost:9057/items'

# PUT_customers_{id}
**Merubah Data pada Customer**
![15 PUT _customers_{id}](https://github.com/gusaditt/Tugas-1-PBO/assets/147296239/3469c0c3-2569-4c5d-9085-1fe4ddfa85df)
diakses dengan 'http://localhost:9057/customers/1'

# PUT_items_{id}
**Merubah Data pada Items**
![17 PUT _items_{id}](https://github.com/gusaditt/Tugas-1-PBO/assets/147296239/fd9aa6ee-972e-4ad8-8d20-9d1862e6f922)
diakses dengan 'http://localhost:9057/items/7'

# DELETE_items{id}
![18 DELETE _items_{id}](https://github.com/gusaditt/Tugas-1-PBO/assets/147296239/60eded91-ba21-47a2-8207-54a67d040cb2)
diakses dengan 'http://localhost:9057/items/2'

# DELETE_customers_{id}_cards_{id}
![19 DELETE _customers_{id}_cards_{id}](https://github.com/gusaditt/Tugas-1-PBO/assets/147296239/c6179074-eda4-4d14-98a5-58fdddcf0233)
diakses dengan 'http://localhost:9057/customers/4/cards/4'
