package ss21_design_patterns.ly_thuyet_va_vi_du.structural_patterns.adapter;

// Target interface - interface mà client mong đợi
public interface MediaPlayer {
    void play(String audioType, String fileName);
}
