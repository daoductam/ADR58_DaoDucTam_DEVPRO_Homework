# Food Order App - ADR58_DaoDucTam_Day7

Một ứng dụng đặt món ăn (Order Food) đơn giản được xây dựng với kiến trúc **Single Activity** và sử dụng **Shared ViewModel** để giao tiếp dữ liệu giữa các Fragment.

## 🌟 Tính năng chính
*   **Danh sách món ăn (FragmentList):** Hiển thị danh sách các công thức nấu ăn với các danh mục (Breakfast, Lunch, Dinner, Snack).
*   **Chi tiết món ăn (FragmentDetail):** Hiển thị thông tin chi tiết về món ăn bao gồm thời gian nấu, độ khó, khẩu phần và nguyên liệu.
*   **Chức năng Favorite (Yêu thích):** Người dùng có thể đánh dấu yêu thích một món ăn ở màn hình chi tiết. Trạng thái này sẽ được cập nhật ngay lập tức và hiển thị biểu tượng trái tim đỏ ở màn hình danh sách.
*   **Giao diện hiện đại:** Thiết kế theo phong cách Pixel Perfect với bo góc lớn (24dp), các biểu tượng tùy chỉnh và màu sắc hài hòa.

## 🛠 Công nghệ sử dụng
*   **Language:** Java
*   **Architecture:** MVVM (với ViewModel và LiveData)
*   **UI Components:** 
    *   Fragment (FragmentList, FragmentDetail)
    *   RecyclerView & Adapter
    *   Material Design Components (MaterialCardView, MaterialButton)
    *   ConstraintLayout
*   **Data Communication:** Shared ViewModel để đồng bộ hóa trạng thái giữa các màn hình mà không cần truyền Bundle phức tạp.

## 📂 Cấu trúc dự án
*   `MainActivity`: Activity duy nhất quản lý việc chuyển đổi giữa các Fragment.
*   `FragmentList`: Hiển thị danh sách món ăn từ ViewModel.
*   `FragmentDetail`: Hiển thị thông tin chi tiết của món ăn đang được chọn.
*   `FoodViewModel`: Chứa dữ liệu danh sách món ăn và quản lý trạng thái món ăn được chọn cũng như trạng thái Favorite.
*   `Food`: Model class chứa thông tin món ăn.
*   `FoodAdapter`: Xử lý việc hiển thị danh sách món ăn trong RecyclerView.

## 🚀 Cách chạy ứng dụng
1.  Mở project trong **Android Studio**.
2.  Đảm bảo các file ảnh PNG (như `telor_ceplok.png`, `salad_vegetarian.png`,...) đã được đặt trong thư mục `app/src/main/res/drawable`.
3.  Build và chạy ứng dụng trên thiết bị mô phỏng hoặc thiết bị thật.

---
*Dự án được thực hiện bởi Đào Đức Tâm - Lớp ADR58.*