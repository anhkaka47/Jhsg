# AI Overlay Assistant — Android starter

## Hiện có
- Ứng dụng Android native Kotlin, không cần máy tính khi chạy sau khi đã cài APK.
- Màn hình chính, Kho Email minh họa, danh sách ứng dụng đã cài.
- Dịch vụ menu nổi có thể kéo, bật/tắt và nút dừng.
- Khai báo Accessibility Service để tích hợp thao tác có xác nhận ở giai đoạn sau.

## Chưa có
- Chưa phải APK đã biên dịch.
- Chưa kết nối mô hình AI.
- Chưa kết nối nhà cung cấp email hoặc lưu email thật.
- Các địa chỉ `example.test` chỉ là dữ liệu minh họa; không có OTP thật.
- Accessibility chưa tự bấm hoặc nhập liệu.

## Build APK
1. Cài Android Studio trên máy tính.
2. Mở thư mục `AIOverlayAssistant`.
3. Chờ Gradle Sync hoàn tất.
4. Chọn Build > Build Bundle(s) / APK(s) > Build APK(s).
5. APK debug nằm tại `app/build/outputs/apk/debug/app-debug.apk`.

Yêu cầu Android SDK Platform 35 và JDK 17 hoặc mới hơn.

## Cấp quyền trên điện thoại
- Mở app > cấp quyền hiển thị trên ứng dụng khác để bật menu nổi.
- Chỉ bật Accessibility nếu bạn chủ động muốn thử tích hợp thao tác; có thể tắt trong Cài đặt Android.

## Bảo mật
Không lưu mật khẩu email hoặc access token dạng văn bản thuần. Khi tích hợp nhà cung cấp thật, dùng OAuth/token scope tối thiểu và Android Keystore. Chỉ kết nối hộp thư mà bạn sở hữu hoặc được phép quản trị.


## Tạo APK debug bằng GitHub Actions (không cần cài Android Studio trên điện thoại)
1. Giải nén dự án và đưa toàn bộ nội dung lên một repository GitHub của bạn.
2. Chọn tab **Actions** trong repository.
3. Chạy workflow **Build Android Debug APK** bằng nút **Run workflow** (hoặc push lên nhánh `main`).
4. Khi workflow hoàn tất, mở lần chạy đó và tải artifact **AIOverlayAssistant-debug**.
5. Giải nén artifact để lấy `app-debug.apk`, sau đó chuyển APK sang điện thoại và cài đặt.

Lưu ý: môi trường tạo tệp ở đây không có Android SDK/Gradle nên chưa thể biên dịch và xác minh APK ngay trong phiên này. Workflow trên sẽ thực hiện build trên GitHub Actions.
