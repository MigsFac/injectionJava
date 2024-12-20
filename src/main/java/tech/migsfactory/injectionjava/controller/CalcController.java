package tech.migsfactory.injectionjava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;


@Controller
public class CalcController{
	@PostMapping("/submit")
	@ResponseBody
	public Map<String,String> submitForm(@RequestParam("kg") double kg,@RequestParam("ri") String ri,Model model) {
		String filePath1 = "static/radioactive_medicines.csv";
		String filePath2 = "static/weight_class.csv";
		
		CSVRead CSV = new CSVRead();
		Map<String, String[]> medicineDb = CSV.readCSV(filePath1);
		Map<String, String[]> weightDb = CSV.readCSV(filePath2);
		
		String NuclideRi = CSV.searchRi(medicineDb,ri);
		
		String classes = medicineDb.get(ri)[2];//各種、医薬品、クラス、基本量、最小量、value
		double baseDose = Double.parseDouble(medicineDb.get(ri)[3]);
		double minDose = Double.parseDouble(medicineDb.get(ri)[4]);
		System.out.println("classes:"+classes);
		linearRegression approximate = new linearRegression();
		double keisuu = approximate.weightClassCalc(weightDb,kg,classes,baseDose,minDose);
		
		
		
		var dose = keisuu;
		String riName = NuclideRi;
		String result;
		if (ri.equals("mag3")) {
			double rasi = kg / 10;
			rasi = Math.round(rasi*10)/10.0;
			
			if(rasi>2) {rasi = 2;}
			result = "体重：" + kg +" kg"+ "<br><br>薬剤名："+ riName + "<br><br>投与量："+dose +" MBq" +"<br><br><span style='font-size:1rem;'>負荷薬剤フロセミド（ラシックス）："+ rasi +" ml</span>";
		} else if(ri.equals("ecd") || ri.equals("imp")) {
			double aczdouble = Math.round(kg *20);
			int acz = (int)aczdouble ;
			if(acz>1000) {acz = 1000;}
			result = "体重：" + kg +" kg"+ "<br><br>薬剤名："+ riName + "<br><br>投与量："+dose +" MBq" +"<br><br><span style='font-size:1rem;'>負荷薬剤ダイアモックス（ACZ）："+ acz +" mg</span>";
		} else {
			result = "体重：" + kg +" kg"+ "<br><br>薬剤名："+ riName + "<br><br>投与量："+dose +" MBq";
		}
		Map<String, String> response = new HashMap<>();
		response.put("result", result);
		return response;
	}
}

