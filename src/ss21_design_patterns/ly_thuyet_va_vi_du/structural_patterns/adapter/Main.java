package ss21_design_patterns.ly_thuyet_va_vi_du.structural_patterns.adapter;

/*
* Adapter Pattern cho phép các interface không tương thích làm việc cùng nhau.
* Nó hoạt động như một cầu nối, chuyển đổi interface của một class thành interface mà client mong đợi.
* */

public class Main {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.play("mp3", "beyond_the_horizon.mp3");
        audioPlayer.play("mp4", "alone.mp4");
        audioPlayer.play("vlc", "far_far_away.vlc");
        audioPlayer.play("avi", "mind_me.avi");
    }
}
