package ss21_design_patterns.ly_thuyet_va_vi_du.structural_patterns.adapter;

// Client
public class AudioPlayer implements MediaPlayer {
    private MediaAdapter mediaAdapter;
    @Override
    public void play(String audioType, String fileName) {
        // Hỗ trợ sẵn định dạng mp3
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Đang phát file MP3: " + fileName);
        }
        // Sử dụng adapter cho các định dạng khác
        else if (audioType.equalsIgnoreCase("vlc") || audioType.equalsIgnoreCase("mp4")) {
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        } else {
            System.out.println("Định dạng " + audioType + " không được hỗ trợ");
        }
    }
}
