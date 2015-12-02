import groovy.json.*
def localpayload = new File('peoplenetrestapi.txt').text
def slurper = new XmlSlurper()
def xmlPayload = null
def vinMap = new LinkedHashMap()
def tmpassetList = []
def list = []

try {

    TimeZone.setDefault(TimeZone.getTimeZone('UTC'))
    def now = new Date()
    def dateString = now.format("yyy-MM-dd'T'HH:mm:ss'Z'")

    xmlPayload = slurper.parseText(localpayload)
        xmlPayload.pnet_get_performx_data_packet.each {
		def vehicledatainfo = []
  		def reportdatainfo = []
                def lochistoryinfo =[]
        it.pxreport.each{
        def vehicle = new LinkedHashMap()    //First linkedHashMap getting the vehicle details
        vehicle['vehicle_number'] = "${it.vehicle_number}"
	vehicle['pxpara_long_idle_thresh'] = "${it.px_params.long_idle_thresh}"
	vehicle['pxpara_rpm_thresh'] = "${it.px_params.rpm_thresh}"
	vehicle['pxpara_over_speed'] = "${it.px_params.over_speed}"
	vehicle['pxpara_excess_speed'] = "${it.px_params.excess_speed}"
     
        vehicledatainfo.push(vehicle)
    }


     it.pxreport.reportdata.each{
	def reportdata = new LinkedHashMap()    //Second LinkedHashmap for all the reportdata fields
        reportdata['reportdata_sd'] = "${it.sd}"
        reportdata['reportdata_ed'] = "${it.ed}"
        reportdata['reportdata_so'] = "${it.so}"
        reportdata['reportdata_eo'] = "${it.eo}"
        reportdata['reportdata_et'] = "${it.et}"
        reportdata['reportdata_mt'] = "${it.mt}"
        reportdata['reportdata_or'] = "${it.or}"
        reportdata['reportdata_os'] = "${it.os}"
        reportdata['reportdata_es'] = "${it.es}"
        reportdata['reportdata_lit'] = "${it.lit}"
        reportdata['reportdata_lic'] = "${it.lic}"
        reportdata['reportdata_sit'] = "${it.sit}"
        reportdata['reportdata_sic'] = "${it.sic}"
        reportdata['reportdata_gof'] = "${it.gof}"
        reportdata['reportdata_vin'] = "${it.vin}"
        reportdata['reportdata_sif'] = "${it.sif}"
        reportdata['reportdata_sf'] = "${it.sf}"
        reportdata['reportdata_ef'] = "${it.ef}"
        reportdata['reportdata_f11'] = "${it.fl1}"
        reportdata['reportdata_f22'] = "${it.fl2}"
       
        reportdatainfo.push(reportdata)        

  }
   
   xmlPayload.pnet_loc_history_packet.loc_history.each{
        def lochistory = new LinkedHashMap()  //Third LinkedHashmap for location history data points
        lochistory['lochistory_truck_number'] = "${it.truck_number}"
        lochistory['lochistory_date_time'] = "${it.datetime}"
        lochistory['lochistory_speed'] = "${it.speed}"
        lochistory['lochistory_heading'] = "${it.heading}"
        lochistory['lochistory_gps_quality'] = "${it.gps_quality}"
        lochistory['lochistory_latitude'] = "${it.latitude}"
        lochistory['lochistory_longitude'] = "${it.longitude}"
        lochistory['lochistory_location'] = "${it.location}"
        lochistory['lochistory_fix_type'] = "${it.fix_type}"
        lochistory['lochistory_ignition'] = "${it.ignition}"
        lochistory['lochistory_gps_odometer'] = "${it.gps_odometer}"
        lochistory['lochistory_gps_rolling_odometer'] = "${it.gps_rolling_odometer}"
        lochistory['lochistory_performx_odometer'] = "${it.performx_odometer}"
        lochistory['lochistory_performx_fuel'] = "${it.performx_fuel}"
        lochistory['lochistory_performx_speed'] = "${it.performx_speed}"
        lochistory['lochistory_performx_idle'] = "${it.performx_idle}"
        lochistory['lochistory_totalPTO'] = "${it.totalPTO}"
        lochistory['lochistory_did1'] = "${it.did1}"
        lochistory['lochistory_driver_LoginID'] = "${it.driver1LoginID}"
        lochistory['lochistory_duty_Status1'] = "${it.dutyStatus1}"
        lochistory['lochistory_did2'] = "${it.did2}"
        lochistory['lochistory_driver2_LoginID'] = "${it.driver2LoginID}"
        lochistory['lochistory_duty_Status2'] = "${it.dutyStatus2}"
        lochistory['lochistory_trailerID1'] = "${it.trailerID1}"
        lochistory['lochistory_traiulerID2'] = "${it.trailerID2}"
        lochistory['lochistory_fueltank1'] = "${it.fuelTank1}"
        lochistory['lochistory_fueltank2'] = "${it.fuelTank2}"
        lochistory['lochistory_ambient_temp'] = "${it.ambientTemp}"
        lochistory['lochistory_pacosID'] = "${it.pacosID}"
        lochistory['load_dts'] = "${dateString}"
   
        lochistoryinfo.push(lochistory)
}

for(int i=0; i<lochistoryinfo.size(); i++){
	if(vehicledatainfo[i] == null){
	def ifnull = new LinkedHashMap()   //Filling empty strings inorder to lineup the data for greenplum sinking by running for loop 
	ifnull['vehicle_number'] = ""
        ifnull['pxpara_long_idle_thresh'] = ""
        ifnull['pxpara_rpm_thresh'] = ""
        ifnull['pxpara_over_speed'] = ""
        ifnull['pxpara_excess_speed'] = ""
   	    vehicledatainfo[i] = ifnull
}
       if(reportdatainfo[i] == null) {
       def ifnull = new LinkedHashMap()
       ifnull['reportdata_sd'] = ""
       ifnull['reportdata_ed'] = ""
       ifnull['reportdata_so'] = ""
       ifnull['reportdata_eo'] = ""
       ifnull['reportdata_et'] = ""
       ifnull['reportdata_mt'] = ""
       ifnull['reportdata_or'] = ""
       ifnull['reportdata_os'] = ""
       ifnull['reportdata_es'] = ""
       ifnull['reportdata_lit'] = ""
       ifnull['reportdata_lic'] = ""
       ifnull['reportdata_sit'] = ""
       ifnull['reportdata_sic'] = ""
       ifnull['reportdata_gof'] = ""
       ifnull['reportdata_vin'] = ""
       ifnull['reportdata_sif'] = ""
       ifnull['reportdata_sf'] = ""
       ifnull['reportdata_ef'] = ""
       ifnull['reportdata_f11'] = ""
       ifnull['reportdata_f22'] = ""
         reportdatainfo[i] = ifnull
} 
       tmpassetList << vehicledatainfo[i] + reportdatainfo[i] + lochistoryinfo[i]
   } 
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
