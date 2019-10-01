package createScreen;

enum format{
	MP3, M4A, OGG;
	
	private String value;
	
	public String getFileFormat() {
		return this.value;
	}
}

enum quality{

	kbp32("32kbps ~0.2mb/minute"),        
	kbp96("96kbps ~0.7mb/minute"),
	kbp128("128kbps ~1mb/minute"),
	kbp192("192kbps ~1.4mb/minute"),
	kbp256("256kbps ~1.9mb/minute"),
	kbp320("320kbps ~2.4mb/minute");
	
	private String value;
	
	quality(String value) {
		// TODO Auto-generated constructor stub
		 this.value= value;
	}
	 public String getQuality() {
		 return this.value;
	 }

}

public class record {


	private String name;	
	String time;
	format format;
	quality quality;
	public void setName(String name){
		this.name=name;
	}
	public String getName() {
		return this.name;
	}
	
}


