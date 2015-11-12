import groovy.json.JsonSlurper
import groovy.json.JsonOutput
import groovy.json.*

def localpayload = new File('telogis.txt').text
def slurper = new JsonSlurper()
def jsonPayload = null
def vinMap = new LinkedHashMap()
def list = []

try {
    
    TimeZone.setDefault(TimeZone.getTimeZone('UTC'))
    def now = new Date()
    def dateString = now.format("yyy-MM-dd'T'HH:mm:ss'Z'")
    jsonPayload = slurper.parseText(localpayload)
    
     jsonPayload.each {
	it.AVLEvents_POC.TableEntry.each {
             current_fleet = it.Vin
             def events = new LinkedHashMap()
             def address1 = new String("${it.Address}") 
             address =  address1.replace("|", " ")
             events['total_fuel_used'] = "${it.TotalFuelUsed}"
             events['unit_id'] = "${it.UnitId}" 
             events['current_odometer'] = "${it.CurrentOdometer}"
             events['total_idle_hours'] = "${it.TotalIdleHours}"
             events['time'] = "${it.Time}"
	     events['driver_id'] = "${it.DriverId}"
             events['battery_voltage'] = "${it.BatteryVoltage}"
	     events['lon'] = "${it.Lon}"
             events['unit_type'] = "${it.UnitType}"
             events['cust_id'] = "${it.CustId}"
             events['brake_engaged'] = "${it.BrakeEngaged}"
             events['seat_belt_fastened'] = "${it.SeatBeltFastened}"
	     events['engine_speed'] = "${it.EngineSpeed}"
	     events['engine_coolant_temp'] = "${it.EngineCoolantTemp}"
             events['fuel_level'] = "${it.FuelLevel}"
             events['event_index'] = "${it.EventIndex}"
             events['fuel_remaining'] = "${it.FuelRemaining}"
             events['engine_on'] = "${it.EngineOn}"
             events['ignition_on'] = "${it.IgnitionOn}"
             events['gps_quality'] = "${it.GPSQuality}"
             events['lattitude'] = "${it.Lat}"
             events['firmware_version'] = "${it.FirmwareVersion}"
             events['total_top_gear_time'] = "${it.TotalTopGearTime}"
             events['current_engine_hours'] = "${it.CurrentEngineHours}"
             events['address'] = "${address}"
             events['total_pto_time'] = "${it.TotalPTOTime}"
             events['speed'] = "${it.Speed}"
             events['throttle_position'] = "${it.ThrottlePosition}"
             events['vin'] = "${it.Vin}"
             events['total_top_gear_fuel'] = "${it.TotalTopGearFuel}"
             events['tag'] = "${it.Tag}"
             events['engine_oil_temp'] = "${it.EngineOilTemp}"
             events['heading'] = "${it.Heading}"
             events['altitude'] = "${it.Altitude}"
             events['total_idle_fuel_used'] = "${it.TotalIdleFuelUsed}"
             events['event_cd'] = "${it.EventCode}"
             events['load_dts'] = "${dateString}"
            // events['fc_fmi'] = vinMap["${current_fleet}"]['fc_fmi']
          //   list << events; 
          




          //  jsonPayload.FaultCodes.TableEntry.each{
          //  def faultcodes = new LinkedHashmap()
             events['fc_fmi'] = "${it.fmi}"
             events['fc_trigger_date_time'] = "${it.trigger_datetime}"
             events['fc_vin'] = "${it.vin}"
             events['fc_cd_count'] = "${it.count}"
             events['fc_spn'] = "${it.spn}"
             events['tsp_provider'] = "${it.tsp_provider}"
             list << events;
	//	} 
	}
    }

//}
} catch (Exception e) {
    println e
}
//def star = [events: list]
 // println star

def returnText = ""
list.each {
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

