import groovy.json.JsonSlurper
import groovy.json.*

def localpayload = new File('geotab.txt').text
def slurper = new JsonSlurper()
def jsonPayload = null
def list = []

try {

       TimeZone.setDefault(TimeZone.getTimeZone('UTC'))
       def now = new Date()
       def dateString = now.format("yyy-MM-dd'T'HH:mm:ss'Z'")

       jsonPayload = slurper.parseText(localpayload)
       jsonPayload.result.each {
	  it.vin_batch.each { 

		
	      def output = new LinkedHashMap()
              //def enable_aux_warning1 = "${it.enableAuxWarning}"
              def star = []
              star = "${it.enableAuxWarning}"
              String str = star.toString()
              str = str.substring(1, str.length() - 1)
              def star1 = []
              star1 = "${it.isAuxInverted}"
              String str1 = star1.toString()
              str1 = str1.substring(1, str1.length() - 1)
              //def star2 =[]
             // def star2 = "${it.deviceFlags.isIridiumAllowed}"
             // String str2 = star2.toString()
             // str2 = str2.substring(1, str2.length() - 1) 
              //star3 =[]
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

              //idef star1 = "${it.autoGroups}"
              //String str1 = star1.toString()
              //str1 = str1.substring(1, str1.length() - 1)
             // def enable_aux_warning1 = "${it.enableAuxWarning}"
             // String enable_aux_warning = enable_aux_warning.toString()
             // enable_aux_warning = enable_aux_warning.substring(1, enable_aux_warning.length() - 1)
              
              //def enable_aux_warning = star.join(", ")
              //def alist = new ArrayList(Arrays.asList(star))
             // println str1
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
              //output['id'] = "${it.major}"
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
              //output['is_audign_trigger'] = "${it.}"
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
              output['major'] = "${it.major}"
              output['load_dts'] = "${dateString}"
              list << output;
		}
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
    csv = csv.getAt(0..lengthMinus1)
    returnText = returnText + csv + "\n"
}
returnText = returnText.trim()
println returnText.replaceAll("\\x00",'')

