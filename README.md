VINAPP
-

1. Mô tả ứng dụng: là một ứng dụng học tập, giải trí và liên hệ gồm các UseCase:
 - UseCase 1 Read: Đọc sách ebook online
 - UseCase 2 Listen: Nghe nhạc online/offline
 - UseCase 3 Game: Chơi trò chơi trên ứng dụng: Cờ vua, cờ tướng,...
 - UseCase 4 News: Xem tin tức xã hội
 - UseCase 5 Call video: Gọi điện video (có thể gọi nhóm)
 - UseCase 6 Message/Chat: Nhắn tin trực tuyến (có Push Notification)
 - UseCase 7 TvShows: Chương trình phim ảnh (có chức năng search theo tên phim)
 - UseCase 8 Share Location: Chia sẻ vị trí của mình với các tài khoản khác trong hệ thống
 - UseCase 9 SignIn/SignOut/SignUp: Đăng nhập, đăng xuất, đăng ký

2. Kiến trúc ứng dụng: MVVM + DataBinding + NavigationCompose + SingleActivity

3. Công nghệ & các thư viện sử dụng: JavaCore + XML + Android SDK
- Jetpack Compose library: Navigation Jetpack Compose
- Design libraries:
  + Size: sdp/ssp dimens
  + Image: RoundedImageView, CircleImageView, Neumorphism
  + Layout: Neumorphism, MaterialDesign(BottomNavigationView,...), ViewPager2, CardView
- Data: 
  + Remote: Retrofit2, Firebase, Picasso (Image)
  + Local: RoomDB, SharePreference

4. Đặc tả UseCase: