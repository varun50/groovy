import groovy.json.*
def localpayload = new File('omnitracs.txt').text
def slurper = new XmlSlurper()
def xmlPayload = null
def vinMap = new LinkedHashMap()
def tmpassetList = []
def list = []

try {

    TimeZone.setDefault(TimeZone.getTimeZone('UTC'))
    def now = new Date()
    def dateString = now.format("yyy-MM-dd'T'HH:mm:ss'Z'")
    def transactioninfo = []
    def faultinfo = []
    xmlPayload = slurper.parseText(localpayload)
	xmlPayload.tran.each {
	if(it.children().find({it.name() == 'T.2.06.0'})) {
        
        
	def transaction = new LinkedHashMap()
        def proximity = new LinkedHashMap()
	transaction['transaction_tranID'] = "${it.@ID}"
	transaction['transaction_companyID'] = "${it.@companyID}"
        transaction['transaction_auxID'] = "${it.@auxID}"
        transaction['event_TS'] = "${it.'T.2.06.0'.eventTS}"
        transaction['equimpent_ID'] = "${it.'T.2.06.0'.equipment.@ID}"	
        transaction['equimpent_equipType'] = "${it.'T.2.06.0'.equipment.@equipType}"
	transaction['equimpent_unit_address'] = "${it.'T.2.06.0'.equipment.@unitAddress}"
	transaction['equimpent_mobileType'] = "${it.'T.2.06.0'.equipment.@mobileType}"
        transaction['equimpent_vin'] = "${it.'T.2.06.0'.equipment.@VIN}"
	transaction['position_lon'] = "${it.'T.2.06.0'.position.@lon}"
	transaction['position_lat'] = "${it.'T.2.06.0'.position.@lat}"
	transaction['position_posTS'] = "${it.'T.2.06.0'.position.@posTS}"
	
	def number_proximity = it.depthFirst().findAll{it.name() ==  'proximity'}
        if(number_proximity.size() == 3){           //running if conditions to get all placeTypes 
	  it.'T.2.06.0'.proximity.each{
		if ("${it.@placeType}" == "CITY") {
	     proximity["proximitycity_postal"] = "${it.@postal}"
	     proximity["proximitycity_country"] = "${it.@country}"
	     proximity["proximitycity_stateProv"] = "${it.@stateProv}"
	     proximity["proximitycity_city"] = "${it.@city}"
	     proximity["proximitycity_direction"] = "${it.@direction}"
	     proximity["proximitycity_distance"] = "${it.@distance}"
	     proximity["proximitycity_placetype"] = "${it.@placeType}"
		}
		if ("${it.@placeType}" == "TOWN") {
             proximity["proximitytown_postal"] = "${it.@postal}"
             proximity["proximitytown_country"] = "${it.@country}"
             proximity["proximitytown_stateProv"] = "${it.@stateProv}"
             proximity["proximitytown_city"] = "${it.@city}"
             proximity["proximitytown_direction"] = "${it.@direction}"
             proximity["proximitytown_distance"] = "${it.@distance}"
             proximity["proximitytown_placetype"] = "${it.@placeType}"
                }
		
 		if ("${it.@placeType}" == "companyLocation") {
             proximity["proximitycompanyLocation_postal"] = "${it.@postal}"
             proximity["proximitycompanyLocation_country"] = "${it.@country}"
             proximity["proximitycompanyLocation_stateProv"] = "${it.@stateProv}"
             proximity["proximitycompanyLocation_city"] = "${it.@city}"
             proximity["proximitycompanyLocation_direction"] = "${it.@direction}"
             proximity["proximitycompanyLocation_distance"] = "${it.@distance}"
             proximity["proximitycompanyLocation_placetype"] = "${it.@placeType}"
                }
		if ("${it.@placeType}" == "dropYard") {
             proximity["proximitydropYard_postal"] = "${it.@postal}"
             proximity["proximitydropYard_country"] = "${it.@country}"
             proximity["proximitydropYard_stateProv"] = "${it.@stateProv}"
             proximity["proximitydropYard_city"] = "${it.@city}"
             proximity["proximitydropYard_direction"] = "${it.@direction}"
             proximity["proximitydropYard_distance"] = "${it.@distance}"
             proximity["proximitydropYard_placetype"] = "${it.@placeType}"
                }
		if ("${it.@placeType}" == "otherMisc") {
             proximity["proximityotherMisc_postal"] = "${it.@postal}"
             proximity["proximityotherMisc_country"] = "${it.@country}"
             proximity["proximityotherMisc_stateProv"] = "${it.@stateProv}"
             proximity["proximityotherMisc_city"] = "${it.@city}"
             proximity["proximityotherMisc_direction"] = "${it.@direction}"
             proximity["proximityotherMisc_distance"] = "${it.@distance}"
             proximity["proximityotherMisc_placetype"] = "${it.@placeType}"
                }
		if ("${it.@placeType}" == "vendorSupplier") {
             proximity["proximityvendorSupplier_postal"] = "${it.@postal}"
             proximity["proximityvendorSupplier_country"] = "${it.@country}"
             proximity["proximityvendorSupplier_stateProv"] = "${it.@stateProv}"
             proximity["proximityvendorSupplier_city"] = "${it.@city}"
             proximity["proximityvendorSupplier_direction"] = "${it.@direction}"
             proximity["proximityvendorSupplier_distance"] = "${it.@distance}"
             proximity["proximityvendorSupplier_placetype"] = "${it.@placeType}"
                }
		if ("${it.@placeType}" == "maintenanceRepair") {
             proximity["proximitymaintenanceRepair_postal"] = "${it.@postal}"
             proximity["proximitymaintenanceRepair_country"] = "${it.@country}"
             proximity["proximitymaintenanceRepair_stateProv"] = "${it.@stateProv}"
             proximity["proximitymaintenanceRepair_city"] = "${it.@city}"
             proximity["proximitymaintenanceRepair_direction"] = "${it.@direction}"
             proximity["proximitymaintenanceRepair_distance"] = "${it.@distance}"
             proximity["proximitymaintenanceRepair_placetype"] = "${it.@placeType}"
                }
		if ("${it.@placeType}" == "customer") {
             proximity["proximitycustomer_postal"] = "${it.@postal}"
             proximity["proximitycustomer_country"] = "${it.@country}"
             proximity["proximitycustomer_stateProv"] = "${it.@stateProv}"
             proximity["proximitycustomer_city"] = "${it.@city}"
             proximity["proximitycustomer_direction"] = "${it.@direction}"
             proximity["proximitycustomer_distance"] = "${it.@distance}"
             proximity["proximitycustomer_placetype"] = "${it.@placeType}"
                }
		if ("${it.@placeType}" == "truckStop") {
             proximity["proximitytruckStop_postal"] = "${it.@postal}"
             proximity["proximitytruckStop_country"] = "${it.@country}"
             proximity["proximitytruckStop_stateProv"] = "${it.@stateProv}"
             proximity["proximitytruckStop_city"] = "${it.@city}"
             proximity["proximitytruckStop_direction"] = "${it.@direction}"
             proximity["proximitytruckStop_distance"] = "${it.@distance}"
             proximity["proximitytruckStop_placetype"] = "${it.@placeType}"
                }
		if ("${it.@placeType}" == "agent") {
             proximity["proximityagent_postal"] = "${it.@postal}"
             proximity["proximityagent_country"] = "${it.@country}"
             proximity["proximityagent_stateProv"] = "${it.@stateProv}"
             proximity["proximityagent_city"] = "${it.@city}"
             proximity["proximityagent_direction"] = "${it.@direction}"
             proximity["proximityagent_distance"] = "${it.@distance}"
             proximity["proximityagent_placetype"] = "${it.@placeType}"
                }
		if ("${it.@placeType}" == "employeeHome") {
             proximity["proximityemployeeHome_postal"] = "${it.@postal}"
             proximity["proximityemployeeHome_country"] = "${it.@country}"
             proximity["proximityemployeeHome_stateProv"] = "${it.@stateProv}"
             proximity["proximityemployeeHome_city"] = "${it.@city}"
             proximity["proximityemployeeHome_direction"] = "${it.@direction}"
             proximity["proximityemployeeHome_distance"] = "${it.@distance}"
             proximity["proximityemployeeHome_placetype"] = "${it.@placeType}"
                }
	}	
	}
	if(number_proximity.size() == 2) {     //Another if condition to get placeTypes where proximity size is 2
	  it.'T.2.06.0'.proximity.each{
	        if ("${it.@placeType}" == "CITY") {
             proximity["proximitycity_postal"] = "${it.@postal}"
             proximity["proximitycity_country"] = "${it.@country}"
             proximity["proximitycity_stateProv"] = "${it.@stateProv}"
             proximity["proximitycity_city"] = "${it.@city}"
             proximity["proximitycity_direction"] = "${it.@direction}"
             proximity["proximitycity_distance"] = "${it.@distance}"
             proximity["proximitycity_placetype"] = "${it.@placeType}"
                }
                if ("${it.@placeType}" == "TOWN") {
             proximity["proximitytown_postal"] = "${it.@postal}"
             proximity["proximitytown_country"] = "${it.@country}"
             proximity["proximitytown_stateProv"] = "${it.@stateProv}"
             proximity["proximitytown_city"] = "${it.@city}"
             proximity["proximitytown_direction"] = "${it.@direction}"
             proximity["proximitytown_distance"] = "${it.@distance}"
             proximity["proximitytown_placetype"] = "${it.@placeType}"
	        }
	    }
	     proximity["proximitytown_postal1"] = ""
             proximity["proximitytown_country1"] = ""
             proximity["proximitytown_stateProv1"] = ""
             proximity["proximitytown_city1"] = ""
             proximity["proximitytown_direction1"] = ""
             proximity["proximitytown_distance1"] = ""
             proximity["proximitytown_placetype1"] = ""
        } 
	
	transaction.putAll(proximity)
	transaction['pos_type'] = "${it.'T.2.06.0'.posType}"
  	transaction['ignition_status'] = "${it.'T.2.06.0'.ignitionStatus}"
	transaction['trip_status'] = "${it.'T.2.06.0'.tripStatus}"
	transaction['ltd_distance'] = "${it.'T.2.06.0'.ltdDistance}"
	transaction['vehicle_speed'] = "${it.'T.2.06.0'.speed}"
	transaction['vehicle_odometer'] = "${it.'T.2.06.0'.odometer}"
	transactioninfo.push(transaction)

}

 	
        if (it.children().find({it.name() == 'T.3.03.0'})) {   //T.3.03.3 is for faultcodes
        
	it.'T.3.03.0'.each{	
	def transactionfault = new LinkedHashMap()
	transactionfault['event_TS_fault'] = "${it.eventTS}"
	transactionfault['equipment_vin_fault'] = "${it.equipment.@VIN}"
	transactionfault['equipiment_equipType_fault'] = "${it.equipment.@equipType}"
	transactionfault['equipment_unit_address_fault'] = "${it.equipment.@unitAddress}"
	transactionfault['equipment_ID_fault'] = "${it.equipment.@ID}"
	transactionfault['position_posTS_fault'] = "${it.position.@posTS}"
	transactionfault['position_lon_fault'] = "${it.position.@lon}"
	transactionfault['position_lat_fault'] = "${it.position.@lat}"
	transactionfault['fault1939_spn'] = "${it.fault1939.spn}"
	transactionfault['fault1939_fmi'] = "${it.fault1939.fmi}"
	transactionfault['fault1939_active'] = "${it.fault1939.active}"
	transactionfault['fault1939_activeTransitionCount'] = "${it.fault1939.activeTransitionCount}"
	transactionfault['load_dts'] = "${dateString}"	
	faultinfo.push(transactionfault)
 } 
}
}
for(int i=0; i<transactioninfo.size(); i++){
        if(faultinfo[i] == null){
        def ifnull = new LinkedHashMap()
	ifnull['event_TS_fault'] = ""
        ifnull['equipment_vin_fault'] = ""
        ifnull['equipiment_equipType_fault'] = ""
        ifnull['equipment_unit_address_fault'] = ""
        ifnull['equipment_ID_fault'] = ""
        ifnull['position_posTS_fault'] = ""
        ifnull['position_lon_fault'] = ""
        ifnull['position_lat_fault'] = ""
        ifnull['fault1939_spn'] = ""
        ifnull['fault1939_fmi'] = ""
        ifnull['fault1939_active'] = ""
        ifnull['fault1939_activeTransitionCount'] = ""
	ifnull['load_dts'] = "${dateString}"
	 faultinfo[i] = ifnull
}
	tmpassetList << transactioninfo[i] + faultinfo[i]

}


} catch (Exception e) {
            println e
}
def returnText = ""
tmpassetList.each {
    def csv = ""
    it.each{
       csv = csv + "${it.getValue()}" + "|"
    }
    def lengthMinus1 = csv.length() - 2
    csv = csv.getAt(0..lengthMinus1)
    returnText = returnText + csv + "\n"
}
returnText = returnText.trim()
println returnText.replaceAll("\\x00",'')
