var data = {};
function getRegId(fname, addr){
	$.ajax({
			url:fname,
			async:false,
			success:function(str){
				var arr = str.split("\n");
				$.each(arr, function(){
					var row = this.split(",");
					var city = row[0];
					var code = row[1];					
					if(city != undefined && code != undefined){
						data[city] = code; 
					}
				});
				
				console.log(data);
			}
	 });
	
	var regId = "1";	
	$.each(data, function(key,value){
		if(addr.match(key)){
				regId = value;	
				console.log("찾은 regId:"+regId);							
		}
	});
	
	console.log("반환하는 regId:"+regId)
	return regId;
}


