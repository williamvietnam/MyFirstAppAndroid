"# ReadListenApp-" 
Ứng dụng đọc sách kết hợp nghe nhạc online/offline
Gồm 4 tab chính: (theo thứ tự)
- tabHome: trc mắt tích hợp chức năng thông tin nhanh, tìm kiếm tại đây, chat qua lại giữa các account
- tabRead: gồm chức năng đọc sách, đọc review sách, viết review, viết bình luận (sử dụng chức năng viết thì phải đăng nhập)
- tabListen: nghe nhạc và nhận xét nhạc (nx nhạc phải đăng nhập)
- tabAccount: quản lý tài khoản cá nhân khi user muốn viết review hay nx bài hát

Công nghệ sử dụng: Java

Flow BookMain: 
-
- BookMain screen --> (click Cửa hàng sách) --> BookStore screen --> (click Xem chi tiết) --> BookCategory screen
  --> (click vào 1 quyển sách) --> BookDetail screen