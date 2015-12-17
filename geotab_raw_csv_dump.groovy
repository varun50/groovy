import groovy.json.JsonSlurper
import groovy.json.*

def localpayload = new File('geotab.txt?dl=0').text
def slurper = new JsonSlurper()
def jsonPayload = null
def list = []
def vinMap = new LinkedHashMap()
def odoMap = new LinkedHashMap()
def fuelMap = new LinkedHashMap()
def failMatch = new LinkedHashMap()
def diagMatch = new LinkedHashMap()
def vinMap1 = new LinkedHashMap()
try {
	jsonPayload = slurper.parseText(payload)
	jsonPayload.result.each {
        it.fail_batch.each {
            failMatch["${it.id}"] = "${it.code}"
        }
        it.diag_batch.each {
            diagMatch["${it.id}"] = "${it.code}"
        }
    }
} catch (Exception e) {
}

try {

       TimeZone.setDefault(TimeZone.getTimeZone('UTC'))
       def now = new Date()
       def dateString = now.format("yyy-MM-dd'T'HH:mm:ss'Z'")
       def vinbatchinfo = []
       def unitinfo = []
       def faultinfo = []       
       jsonPayload = slurper.parseText(localpayload)
       jsonPayload.result.each {
	  it.vin_batch.each { 

		
	      def output = new LinkedHashMap()
              def star = []
              star = "${it.enableAuxWarning}"
              String str = star.toString()
              str = str.substring(1, str.length() - 1)
              def star1 = []
              star1 = "${it.isAuxInverted}"
              String str1 = star1.toString()
              str1 = str1.substring(1, str1.length() - 1)
              def star3 = "${it.groups.id}"
              String str3 = star3.toString()
              str3 = str3.substring(1, str3.length() - 2) 
              def star4 = "${it.customParameters}"
              String str4 = star4.toString()
              str4 = str4.substring(1, str4.length() - 1)
              def star5 = "${it.autoGroups}"
              String str5 = star5.toString()
              str5 = str5.substring(1, str5.length() - 1)
              def star6 = "${it.groups.children}"
              String str6 = star6.toString()
              str6 = str6.substring(1, str6.length() - 3)
              def star7 = "${it.devicePlans}"
              String str7 = star7.toString()
              str7 = str7.substring(1, str7.length() - 1)
              def star8 = "${it.deviceFlags.ratePlans}"
              String str8 = star8.toString()
              str8 = str8.substring(1, str8.length() - 1)
              def star9 = "${it.isAuxIgnTrigger}"
              String str9 = star9.toString()
              str9 = str9.substring(1, str9.length() - 1)            

	      output['comment'] = "${it.comment}"
              output['enable_speed_warning'] = "${it.enableSpeedWarning}"
              output['major'] = "${it.major}"
	      output['licence_plate'] = "${it.licensePlate}"
              output['enable_must_reprogram'] = "${it.enableMustReprogram}"
              output['immobllize_arming'] = "${it.immobilizeArming}"
              output['engine_vehicle_identification_number'] = "${it.vehicleIdentificationNumber}"
              output['is_active_tracking_enabled'] = "${it.isActiveTrackingEnabled}"
              output['work_time'] = "${it.workTime}"
              output['is_reverse_detection'] = "${it.isReverseDetectOn}"
              output['braking_warning_treshold'] = "${it.brakingWarningThreshold}"
	      output['gps_off_delay'] = "${it.gpsOffDelay}"
	      output['min_accident_speed'] = "${it.minAccidentSpeed}"
              output['serial_number'] = "${it.serialNumber}"
              output['time_to_download'] = "${it.timeToDownload}"
              output['max_seconds_between_logs'] = "${it.maxSecondsBetweenLogs}"
              output['is_speed_indicator'] = "${it.isSpeedIndicator}"
              output['custom_parameter'] = "${str4}"
              output['hardware_id'] = "${it.hardwareId}"
              output['name'] = "${it.name}"
              output['active_form'] = "${it.activeFrom}"
              output['cornering_warning_threshold'] = "${it.corneringWarningThreshold}"
              output['device_type'] = "${it.deviceType}"
              output['disable_buzzer'] = "${it.disableBuzzer}"
              output['accelerometer_threshold_warning_factor'] = "${it.accelerationWarningThresholdFactor}"
              output['speeding_off'] = "${it.speedingOff}"
              output['minor'] = "${it.minor}"
              output['product_id'] = "${it.productId}"
              output['odometer_off_set'] = "${it.odometerOffset}"
              output['is_driver_seat_belt_warning_on'] = "${it.isDriverSeatbeltWarningOn}"
              output['is_iridium_in_use'] = "${it.deviceFlags.isIridiumInUse}"
              output['is_vin_allowed'] = "${it.deviceFlags.isVINAllowed}"
              output['is_odometer_allowed'] = "${it.deviceFlags.isOdometerAllowed}"
              output['is_trip_detail_allowed'] = "${it.deviceFlags.isTripDetailAllowed}"
              output['is_iridium_allowed'] = "${it.deviceFlags.isIridiumAllowed}"
              output['is_engine_allowed'] = "${it.deviceFlags.isEngineAllowed}"
              output['is_gramin_in_use'] = "${it.deviceFlags.isGarminInUse}"
              output['is_garmine_allowed'] = "${it.deviceFlags.isGarminAllowed}"
              output['rate_plans'] = "${str8}"
              output['is_active_tracking_allowed'] = "${it.deviceFlags.isActiveTrackingAllowed}"
              output['is_hos_in_use'] = "${it.deviceFlags.isHOSInUse}"
              output['is_ui_allowed'] = "${it.deviceFlags.isUIAllowed}"
              output['is_hos_allowed'] = "${it.deviceFlags.isHOSAllowed}"
              output['ignore_downloads_until'] = "${it.ignoreDownloadsUntil}"
              output['is_audign_trigger'] = "${str9}"
              output['odometer_factor'] = "${it.odometerFactor}"
              output['external_device_shut_down_delay'] = "${it.externalDeviceShutDownDelay}"
              output['pin_device'] = "${it.pinDevice}"
              output['active_to'] = "${it.activeTo}"
              output['license_state'] = "${it.licenseState}"
              output['vehicle_identification_number'] = "${it.vehicleIdentificationNumber}"
              output['enable_beep_on_dangerous_driving'] = "${it.enableBeepOnDangerousDriving}"
              output['groups_children'] = "${str6}"
              output['groups_id'] = "${str3}"
              output['device_plans'] = "${str7}"
              output['idle_minutes'] = "${it.idleMinutes}"
              output['acceleration_warning_threshold'] = "${it.accelerationWarningThreshold}"
              output['timezone_id'] = "${it.timeZoneId}"
              output['enable_beep_on_rpm'] = "${it.enableBeepOnRpm}"
              output['enable_control_external_relay'] = "${it.enableControlExternalRelay}"
              output['immobolize_unit'] = "${it.immobilizeUnit}"
              output['is_aux_inverted'] = "${str1}"
              output['auto_groups'] = "${str5}"
              output['ensure_hot_start'] = "${it.ensureHotStart}"
              output['enable_beep_on_idle'] = "${it.enableBeepOnIdle}"
              output['speeding_on'] = "${it.speedingOn}"
              output['enable_aux_warning'] = "${str}"
              output['parameter_version'] = "${it.parameterVersion}"
              output['engine_type'] = "${it.engineType}"
              output['rmp_value'] = "${it.rpmValue}"
           //   output['major'] = "${it.major}"
              //output['load_dts'] = "${dateString}"
	      vinbatchinfo.push(output)
		}
	
	if (it.odo_batch) {
            it.odo_batch.each {
                odoMap[it.device.id] = (int)(Math.round((float)(it.data / 1609.34)))
            }
        }
        if (it.fuellevel_batch) {
            it.fuellevel_batch.each {
                fuelMap[it.device.id] = (int)(Math.round((float)it.data))
            }
        }
        if (it.vin_batch) {
            it.vin_batch.each { 
                vinMap1[it.id] = it.vehicleIdentificationNumber
            }
        }
		it.loc_batch.each {
	         def output = new LinkedHashMap()
            if ( "${it.speed}".size() !=0 ) {
                def speedFloat = Float.parseFloat("${it.speed}")
                speedFloat = speedFloat * 0.62137 
                def speedInt = (int)(Math.round(speedFloat)) 
                output['speed'] = "${speedInt}"
            }
            else {
                output['speed'] = "" 
            }
            if ( "${it.bearing}".size() != 0 ) {
                def headingInt = Integer.parseInt("${it.bearing}")
                def headingFinal = ""

                if (headingInt < 0) {
                    headingFinal += "SE"
                }
                if (headingInt == 0) {
                    headingFinal += "E"
                }
                if (headingInt == 90) {
                    headingFinal += "N"
                }
                if (headingInt == 180) {
                    headingFinal += "W"
                }
                if (headingInt == 270) {
                    headingFinal += "S"
                }
                if (headingInt > 0 && headingInt < 90) {
                    headingFinal += "NE"
                }
                if (headingInt > 90 && headingInt < 180) {
                    headingFinal += "NW"
                }
                if (headingInt > 180 && headingInt < 270) {
                    headingFinal += "SW"
                }
                if (headingInt > 270 && headingInt < 360) {
                    headingFinal += "SE"
                }
                output['heading'] = "${headingFinal}"
            }	
            else {
                output['heading'] = ""
            }
	        output['vin'] = vinMap1["${it.device.id}"]
                output['fuel_level'] = fuelMap["${it.device.id}"].toString()
                output['odometer'] = odoMap["${it.device.id}"].toString()
                output['read_datetime'] = "${dateString}"
                output['capture_datetime'] = "${it.dateTime}"
	        output['latitude'] = "${it.latitude}"
	        output['longitude'] = "${it.longitude}"
                output['fault_code_id'] = vinMap1["${it.device.id}"]+"-${dateString}-geotab"
	        output['tsp_provider'] = "geotab"
                output['flag'] = "UT"
	        unitinfo.push(output)
	}

        it.vin_batch.each {
            vinMap["${it.id}"] = "${it.vehicleIdentificationNumber}"
        }
	it.fault_batch.each {
            def output = new LinkedHashMap()
            if ("${it.failureMode}" != "NoFailureModeId") { 
               if (failMatch["${it.failureMode.id}"] == null) {
                    output['vin1'] = vinMap["${it.device.id}"]
                    output['spn'] = diagMatch["${it.diagnostic.id}"]
                    output['fmi'] = "null" 
                    output['read_datetime1'] = "${dateString}"
                    output['trigger_datetime'] = "${it.dateTime}"
                    output['capture_datetime1'] = "tsp_na"
                    output['event_datetime'] = "${it.dateTime}" // added since Geotab provides trigger date time
                    output['engine_coolant'] = "tsp_na"
                    output['oil_pressure'] = "tsp_na"
                    output['tsp_provider1'] = "geotab"
                    output['status'] = "tsp_na"
                    output['fault_code_count'] = "${it.count}"
                    output['fault_code_id1'] = vinMap["${it.device.id}"] + "-${dateString}-" + "geotab"
                    output['flag1'] = "UFCH"
	            output['load_dts'] = "${dateString}"
                    faultinfo.push(output)				

	    }
                else {
                    output['vin1'] = vinMap["${it.device.id}"]
                    output['spn'] = diagMatch["${it.diagnostic.id}"]
                    output['fmi'] = failMatch["${it.failureMode.id}"]
                    output['read_datetime1'] = "${dateString}"
                    output['trigger_datetime1'] = "${it.dateTime}"
                    output['capture_datetime'] = "tsp_na"
                    output['event_datetime'] = "${it.dateTime}" // added since Geotab provides trigger date time
                    output['engine_coolant'] = "tsp_na"
                    output['oil_pressure'] = "tsp_na"
                    output['tsp_provider1'] = "geotab"
                    output['status'] = "tsp_na"
                    output['fault_code_count'] = "${it.count}"
                    output['fault_code_id1'] = vinMap["${it.device.id}"] + "-${dateString}-" + "geotab"
                    output['flag1'] = "UFCH"
	            output['load_dts'] = "${dateString}"
                    faultinfo.push(output)
               }
            }
       }
	
}
for(int i=0; i<faultinfo.size(); i++){
        if(unitinfo[i] == null){
        def ifnull = new LinkedHashMap()
	ifnull ['speed'] = ""
	ifnull ['heading'] = ""
	ifnull['vin'] = ""
        ifnull['fuel_level'] = ""
        ifnull['odometer'] = ""
        ifnull['read_datetime'] = ""
        ifnull['capture_datetime'] = ""
        ifnull['latitude'] = ""
        ifnull['longitude'] = ""
        ifnull['fault_code_id'] = ""
        ifnull['tsp_provider'] = ""
        ifnull['flag'] = ""
	unitinfo[i] = ifnull
        }
	if(vinbatchinfo[i] == null) {
	def output = new LinkedHashMap()
	      output['comment'] = ""
              output['enable_speed_warning'] = ""
              output['major'] = ""
	      output['licence_plate'] = ""
              output['enable_must_reprogram'] = ""
              output['immobllize_arming'] = ""
              output['engine_vehicle_identification_number'] = ""
              output['is_active_tracking_enabled'] = ""
              output['work_time'] = ""
              output['is_reverse_detection'] = ""
              output['braking_warning_treshold'] = ""
	      output['gps_off_delay'] = ""
	      output['min_accident_speed'] = ""
              output['serial_number'] = ""
              output['time_to_download'] = ""
              output['max_seconds_between_logs'] = ""
              output['is_speed_indicator'] = ""
              output['custom_parameter'] = ""
              output['hardware_id'] = ""
              output['name'] = ""
              output['active_form'] = ""
              output['cornering_warning_threshold'] = ""
              output['device_type'] = ""
              output['disable_buzzer'] = ""
              output['accelerometer_threshold_warning_factor'] = ""
              output['speeding_off'] = ""
              output['minor'] = ""
              output['product_id'] = ""
              output['odometer_off_set'] = ""
              output['is_driver_seat_belt_warning_on'] = ""
              output['is_iridium_in_use'] = ""
              output['is_vin_allowed'] = ""
              output['is_odometer_allowed'] = ""
              output['is_trip_detail_allowed'] = ""
              output['is_iridium_allowed'] = ""
              output['is_engine_allowed'] = ""
              output['is_gramin_in_use'] = ""
              output['is_garmine_allowed'] = ""
              output['rate_plans'] = ""
              output['is_active_tracking_allowed'] = ""
              output['is_hos_in_use'] = ""
              output['is_ui_allowed'] = ""
              output['is_hos_allowed'] = ""
              output['ignore_downloads_until'] = ""
              output['is_audign_trigger'] = ""
              output['odometer_factor'] = ""
              output['external_device_shut_down_delay'] = ""
              output['pin_device'] = ""
              output['active_to'] = ""
              output['license_state'] = ""
              output['vehicle_identification_number'] = ""
              output['enable_beep_on_dangerous_driving'] = ""
              output['groups_children'] = ""
              output['groups_id'] = ""
              output['device_plans'] = ""
              output['idle_minutes'] = ""
              output['acceleration_warning_threshold'] = ""
              output['timezone_id'] = ""
              output['enable_beep_on_rpm'] = ""
              output['enable_control_external_relay'] = ""
              output['immobolize_unit'] = ""
              output['is_aux_inverted'] = ""
              output['auto_groups'] = ""
              output['ensure_hot_start'] = ""
              output['enable_beep_on_idle'] = ""
              output['speeding_on'] = ""
              output['enable_aux_warning'] = ""
              output['parameter_version'] = ""
              output['engine_type'] = ""
              output['rmp_value'] = ""
        //      output['major'] = ""

              vinbatchinfo[i] = output
	}
	list << vinbatchinfo[i] + unitinfo[i] + faultinfo[i]

}
} catch (Exception e) {
    println e
}

def returnText = ""
list.each {
    def csv = ""
    it.each{
       csv = csv + "${it.getValue()}" + "|"
    }
    def lengthMinus1 = csv.length() - 2
    csv = csv.getAt(0..lengthMinus1).substring(1)
    returnText = returnText + csv + "\n"
}
returnText = returnText.trim()
println returnText.replaceAll("\\x00",'')

