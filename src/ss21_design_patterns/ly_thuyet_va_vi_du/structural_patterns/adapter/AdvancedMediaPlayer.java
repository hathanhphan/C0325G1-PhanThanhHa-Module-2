package ss21_design_patterns.ly_thuyet_va_vi_du.structural_patterns.adapter;

// Adaptee - class có interface không tương thích
public class AdvancedMediaPlayer {
    public void playVlc(String fileName) {
        System.out.println("Đang phát file VLC: " + fileName);
    }

    public void playMp4(String fileName) {
        System.out.println("Đang phát file MP4: " + fileName);
    }
}
