package board.file.action;

import java.text.DecimalFormat;

public class SizeCaculation {
	
	public String SizeCalcu(int file_size) {
		String CalcuSize = null;
		int i = 0;
		double calcu = (double)file_size;
		
		while (calcu >= 1024 && i < 3){ // 단위 숫자로 나누고 한번 나눌 때마다 i 증가
		   calcu = calcu / 1024;
		   i++;
		}
		
		DecimalFormat df = new DecimalFormat("##0.0");
		    
			switch (i) {
		        case 0:
		            CalcuSize = df.format(calcu) + "Byte";
		            break;
		        case 1:
		            CalcuSize = df.format(calcu) + "KB";
		            break;
		        case 2:
		            CalcuSize = df.format(calcu) + "MB";
		            break;
		    }

		
	return CalcuSize;
}
}