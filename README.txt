Thao tác cài đặt thực thi code:
- Tải XAMPP
- Tải source code về giải nén vào thư mục R2S vừa tạo đặt trong thư mục htdocs của XAMPP
- Vào đường dẫn R2S/r2s-calendar/Service
- Mở CMD/ GITBash/ Terminal, gõ dòng lệnh 
php composer.phar install
php composer.phar update
- Start sever bằng lệnh: php artisan serve --host=ipmay --port=8000
- Sau đó vào trình duyệt, gõ http://ipmay:8000 và ra được giao diện Laravel thì sever đang hoạt động
- Vào mở source code Ui bằng android studio, tìm đến res/xml/network_security_config và com.uiapp.r2scalendar/Utils/ConfigEnum
và sau đó chuyển ip trong 2 file này thành ipmay
- Test code thôi