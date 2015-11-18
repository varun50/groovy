import groovy.json.JsonSlurper
import groovy.json.JsonOutput

def slurper = new JsonSlurper()
def jsonPayload = null;
//def output = new LinkedHashMap()
def localpayload = new File('calampsample2.txt').text
def vinMap = new LinkedHashMap()
def finalmap = new LinkedHashMap()
def finalList = []

try {
    TimeZone.setDefault(TimeZone.getTimeZone('UTC'))
    def now = new Date()
    def dateString = now.format("yyy-MM-dd'T'HH:mm:ss'Z'")

    jsonPayload = slurper.parseText(localpayload)
    jsonPayload.response.results.each {
         //it.events.each { 
        	def jbusEvents = []
			def dailyEvents = []
			def hourlyEvents = []
			def avlEvents = []
			
			it.events.jbusDtc1939Events.each{
				def events = new LinkedHashMap()
				def protocol = new LinkedHashMap()
				events["eventTime"] = "${it.jbusDtc1939Event.eventTime}"
				events["event_device_esn"] = "${it.jbusDtc1939Event.deviceEsn}"
  				events["event_device_id"] = "${it.jbusDtc1939Event.deviceId}"
                                events["event_device_air_id"] = "${it.jbusDtc1939Event.deviceAirId}"
				events["event_source_address"] = "${it.jbusDtc1939Event.sourceAddress}"
                                 it.jbusDtc1939Event.jbusDtc1939ProtocolEvents.each{	
					protocol["event_oc"] = "${it.jbusDtc1939Protocol.oc}"
                                        protocol["event_spn"] = "${it.jbusDtc1939Protocol.spn}"
                                        protocol["event_fmi"] = "${it.jbusDtc1939Protocol.fmi}"
				}
				events.putAll(protocol)
				jbusEvents.push(events)
			}
		

                       it.events.jbusHourlyReportEvents.each{
                            def hourlyEventsMap = new LinkedHashMap()
                                hourlyEventsMap["hourly_event_time"] = "${it.jbusHourlyReportEvent.eventTime}"
                                hourlyEventsMap["event_engine_battery"] = "${it.jbusHourlyReportEvent.engineBatteryVoltage}"
                                hourlyEventsMap["hourly_voltage"] = "${it.jbusHourlyReportEvent.engineBatteryVoltage}"
                                hourlyEventsMap["hourly_engine_coolant_temp"] = "${it.jbusHourlyReportEvent.engineCoolantTemperature}"
                                hourlyEventsMap["hourly_device_esn"] = "${it.jbusHourlyReportEvent.deviceEsn}"
                                hourlyEventsMap["hourly_engine_coolant_pressure"] = "${it.jbusHourlyReportEvent.engineCoolantPressure}"
				hourlyEventsMap["hourly_engine_fuel_tank_level_2"] = "${it.jbusHourlyReportEvent.engineFuelTankLevel2}"
				hourlyEventsMap["hourly_transmission_oil_temp"] = "${it.jbusHourlyReportEvent.transmissionOilTemperature}"
				hourlyEventsMap["hourly_device_id"] = "${it.jbusHourlyReportEvent.deviceId}"
				hourlyEventsMap["hourly_avg_fuel_economy"] = "${it.jbusHourlyReportEvent.averageFuelEconomy}"
				hourlyEventsMap["hourly_engine_crank_case_pressure"] = "${it.jbusHourlyReportEvent.engineCrankcasePressure}"
                                hourlyEventsMap["houtly_hourly_device_air_id_1"] = "${it.jbusHourlyReportEvent.deviceAirId}"
				hourlyEventsMap["hourly_engine_oil_temperature"] = "${it.jbusHourlyReportEvent.engineOilTemperature}"
                                hourlyEventsMap["hourly_engine_fuel_tank_level_1"] = "${it.jbusHourlyReportEvent.engineFuelTankLevel1}"
				hourlyEventsMap["hourly_engine_oil_pressure"] = "${it.jbusHourlyReportEvent.engineOilPressure}"
                                hourlyEvents.push(hourlyEventsMap)
                        }


                        it.events.jbusDailyReportEvents.each{
				def dailyEventMap = new LinkedHashMap()
				dailyEventMap["daily_engine_coolant_level"] = "${it.jbusDailyReportEvent.engineCoolantLevel}"
				dailyEventMap["daily_event_time"] = "${it.jbusDailyReportEvent.eventTime}"
				dailyEventMap["daily_engine_total_hours"] = "${it.jbusDailyReportEvent.engineTotalHours}"
				dailyEventMap["daily_engine_idle_fuel"] = "${it.jbusDailyReportEvent.engineIdleFuel}"
				dailyEventMap["daily_engine_idle_hours"] = "${it.jbusDailyReportEvent.engineIdleHours}"
				dailyEventMap["daily_device_esn"] = "${it.jbusDailyReportEvent.deviceEsn}"
				dailyEventMap["daily_device_id"] = "${it.jbusDailyReportEvent.deviceId}"
				dailyEventMap["daily_nox_tank_level"] = "${it.jbusDailyReportEvent.noxTankLevel}"
				dailyEventMap["daily_device_air_id"] = "${it.jbusDailyReportEvent.deviceAirId}"
				dailyEventMap["daily_engine_oil_level"] = "${it.jbusDailyReportEvent.engineOilLevel}"
				dailyEvents.push(dailyEventMap)
			}
			
			it.events.avlEvents.each{
			    def avlEventsMap = new LinkedHashMap()
                                avlEventsMap["avlevent_event_time"] = "${it.avlEvent.eventTime}"
				avlEventsMap["avlevent_event_type"] = "${it.avlEvent.eventType}"
				avlEventsMap["avl_event_telemrtry_altitude"] = "${it.avlEvent.telemetry.telemetry.altitude}"
				avlEventsMap["avl_event_telemetry_speed"] = "${it.avlEvent.telemetry.telemetry.speed}"
				avlEventsMap["avl_event_telemetry_device_esn"] = "${it.avlEvent.deviceEsn}"
				avlEventsMap["avl_event_telemetry_device_id"] = "${it.avlEvent.deviceId}"
				avlEventsMap["avl_event_telemetry_vin"] = "${it.avlEvent.vin}"
				avlEventsMap["avl_event_telemetry_device_air_id"] = "${it.avlEvent.deviceAirId}"
				avlEventsMap["avl_event_gps_hdop"] = "${it.avlEvent.gps.gps.hdop}"
				avlEventsMap["avl_event_gps_longitude"] = "${it.avlEvent.gps.gps.longitude}"
				avlEventsMap["avl_event_gps_satellites"] = "${it.avlEvent.gps.gps.satellites}"
				avlEventsMap["avl_event_carrier"] = "${it.avlEvent.gps.gps.carrier}"
				avlEventsMap["avl_event_latitude"] = "${it.avlEvent.gps.gps.latitude}"
				avlEventsMap["avl_event_gps_validity"] = "${it.avlEvent.gps.gps.gpsValidity}"
				avlEventsMap["avl_event_gps_heading"] = "${it.avlEvent.gps.gps.heading}"
			    	avlEventsMap["avl_event_gps_load_dts"] = "${dateString}"
				avlEvents.push(avlEventsMap)
			}
		//println avlEvents.size()	
			for(int i=0; i<avlEvents.size(); i++){
				if(jbusEvents[i] == null){
                                 def ifnull = new LinkedHashMap()
			//	ifnull["nullvalue"] = ""
 				ifnull["eventTime"] = ""
                                ifnull["event_device_esn"] = ""
                                ifnull["event_device_id"] = ""
                                ifnull["event_device_air_id"] = ""
                                ifnull["event_source_address"] = ""
                                ifnull["events_oc"] = ""
				ifnull["events_spn"] = ""
				ifnull["events_fmi"] = ""
                                       jbusEvents[i] = ifnull
                                 //      jbusEvents.push(ifnull)
				}
                                if(dailyEvents[i] == null) {
                                 def ifnull = new LinkedHashMap()
                              //   ifnull1["nullvalues"] = ""
                                ifnull["hourly_event_time"] = ""
                                ifnull["event_engine_battery"] = ""
				ifnull["hourly_voltage"] = ""
                                ifnull["hourly_engine_coolant_temp"] = ""
                                ifnull["hourly_device_esn"] = ""
                                ifnull["hourly_engine_coolant_pressure"] = ""
                                ifnull["hourly_engine_fuel_tank_level_2"] = ""
                                ifnull["hourly_transmission_oil_temp"] = ""
                                ifnull["hourly_device_id"] = ""
                                ifnull["hourly_avg_fuel_economy"] = ""
                                ifnull["hourly_engine_crank_case_pressure"] = ""
                                ifnull["houtly_hourly_device_air_id_1"] = ""
                                ifnull["hourly_engine_oil_temperature"] = ""
                                ifnull["hourly_engine_fuel_tank_level_1"] = ""
                                ifnull["hourly_engine_oil_pressure"] = ""   
                                     dailyEvents[i] = ifnull
                                //     dailyEvents.push(ifnull1)
                               }
                               if(hourlyEvents[i] == null) {
                                 def ifnull = new LinkedHashMap()
				ifnull["daily_engine_coolant_level"] = ""
                                ifnull["daily_event_time"] = ""
                                ifnull["daily_engine_total_hours"] = ""
                                ifnull["daily_engine_idle_fuel"] = ""
                                ifnull["daily_engine_idle_hours"] = ""
                                ifnull["daily_device_esn"] = ""
                                ifnull["daily_device_id"] = ""
                                ifnull["daily_nox_tank_level"] = ""
                                ifnull["daily_device_air_id"] = ""
                                ifnull["daily_engine_oil_level"] = ""
                               //  ifnull2["nullvalues"] = ""      
                                  hourlyEvents[i] = ifnull
				//  hourlyEvents.push(ifnull2)
                               }
		    		finalList << jbusEvents[i] + dailyEvents[i] + hourlyEvents[i] + avlEvents[i]
			//	finalList << jbusEvents + dailyEvents + hourlyEvents + avlEvents
		//		println finalList
			}
		//	println finalList
		//	println finalList.size()	
			//list << hourlyEvents[0]
			
     }
		 } catch (Exception e) {
		      println e
		 }
		 //println finalList

def returnText = ""
finalList.each {
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
