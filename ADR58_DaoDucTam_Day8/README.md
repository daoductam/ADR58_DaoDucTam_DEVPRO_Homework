# My Notes App

Một ứng dụng ghi chú đơn giản được xây dựng bằng ngôn ngữ Java, tuân thủ thiết kế Material Design và sử dụng Room Database để lưu trữ dữ liệu bền vững.

## 📸 Demo
Dưới đây là giao diện chính xác của ứng dụng:

![App Demo](app/src/main/res/drawable/img.png)

## 🚀 Tính năng chính (Full CRUD & Search)

- **Xem danh sách (Read)**: Tự động hiển thị các ghi chú đã lưu từ Room Database.
- **Thêm ghi chú (Create)**: Cho phép nhập tiêu đề và chọn loại ghi chú (YouTube, Voice, Folder).
- **Cập nhật (Update)**: Nhấn vào ghi chú bất kỳ để thay đổi thông tin.
- **Xóa (Delete)**: Nhấn giữ (Long click) vào ghi chú để xóa khỏi ứng dụng.
- **Tìm kiếm (Search)**: Lọc ghi chú theo thời gian thực ngay khi nhập tiêu đề vào ô tìm kiếm.
- **Dữ liệu mẫu**: Tự động tạo dữ liệu mẫu nếu ứng dụng trống trong lần chạy đầu tiên.

## 🛠 Công nghệ sử dụng

- **Ngôn ngữ**: Java (Cú pháp đơn giản, không dùng ViewBinding).
- **Database**: Room Persistence Library.
- **UI Components**: Material Components, ConstraintLayout, RecyclerView, CardView.
- **Architecture**: Mô hình DAO (Data Access Object) và Singleton cho Database.

## 📁 Cấu trúc thư mục chính

- `MainActivity.java`: Màn hình chính, xử lý danh sách, tìm kiếm và xóa.
- `AddNoteActivity.java`: Màn hình thêm và sửa ghi chú.
- `NoteAdapter.java`: Bộ điều khiển hiển thị dữ liệu lên RecyclerView.
- `NoteDatabase.java`, `NoteDao.java`, `Note.java`: Cấu trúc dữ liệu và Database.

## ⚙️ Cài đặt
1. Mở project trong Android Studio (phiên bản Ladybug hoặc mới hơn).
2. Đảm bảo đã cài đặt SDK 35.
3. Nhấn **Sync Project with Gradle Files**.
4. Chạy trên máy ảo hoặc thiết bị thật (API 24+).

---
*Phát triển bởi Đào Đức Tâm - ADR58*