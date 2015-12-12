import groovy.json.*
def localpayload = new File('xrs.txt').text
def slurper = new XmlSlurper()
def xmlPayload = null
def vinMap = new LinkedHashMap()
def tmpassetList = []
def list = []

try {

    TimeZone.setDefault(TimeZone.getTimeZone('UTC'))
    def now = new Date()
    def dateString = now.format("yyy-MM-dd'T'HH:mm:ss'Z'")
      def vehiclestatusinfo = []
      def vehicleinfo = []
      def faultcodeinfo = [] 
       xmlPayload = slurper.parseText(localpayload)
	  xmlPayload.ArrayOfVehicleStatus.VehicleStatus.each {
	  def vehiclestatus = new LinkedHashMap()
	  vehiclestatus['vehiclestatus_direction'] = "${it.Direction}"
	  vehiclestatus['vehiclestatus_driverId'] = "${it.DriverID}"
	  vehiclestatus['vehiclestatus_driver_name'] = "${it.DriverName}"
	  vehiclestatus['vehiclestatus_driver_organization_name'] = "${it.DriverOrganizationName}"
	  vehiclestatus['vehiclestatus_driver_organization_SID'] = "${it.DriverOrganizationSID}"
	  vehiclestatus['vehiclestatus_driverSID'] = "${it.DriverSID}"
	  vehiclestatus['vehiclestatus_engine_hours'] = "${it.EngineHours}"
	  vehiclestatus['vehiclestatus_engine_speed'] = "${it.EngineSpeed}"
	  vehiclestatus['vehiclestatus_heading'] = "${it.Heading}"
	  vehiclestatus['vehiclestatus_ignition_status'] = "${it.IgnitionStatus}"
	  vehiclestatus['vehiclestatus_last_update'] = "${it.LastUpdate}"
          vehiclestatus['vehiclestatus_motion_state'] = "${it.MotionState}"
          vehiclestatus['vehiclestatus_odometer'] = "${it.Odometer}"
          vehiclestatus['vehiclestatus_site_latitude'] = "${it.SiteLatitude}"
          vehiclestatus['vehiclestatus_site_longitude'] = "${it.SiteLongitude}"
          vehiclestatus['vehiclestatus_site_SID'] = "${it.SiteSID}"
          vehiclestatus['vehiclestatus_speed'] = "${it.speed}"
          vehiclestatus['vehiclestatus_vehicle_latitude'] = "${it.VehicleLatitude}"
          vehiclestatus['vehiclestatus_vehicle_location'] = "${it.VehicleLocation}"
          vehiclestatus['vehiclestatus_vehicle_longitude'] = "${it.VehicleLongitude}"
	  vehiclestatus['vehiclestatus_vehicle_name'] = "${it.VehicleName}"
          vehiclestatus['vehiclestatus_vehicle_organization_name'] = "${it.VehicleOrganizationName}"
          vehiclestatus['vehiclestatus_vehicle_organization_SID'] = "${it.VehicleOrganizationSID}"
          vehiclestatus['vehiclestatus_vehicle_SID'] = "${it.VehicleSID}"
	 // tmpassetList << vehiclestatus
	  vehiclestatusinfo.push(vehiclestatus)
         }
	 xmlPayload.ArrayOfVehicle.Vehicle.each {
	 def vehicle = new LinkedHashMap()
	 vehicle['vehicle_auxiliary'] = "${it.Auxiliary}"
	 vehicle['vehicle_company_name'] = "${it.CompanyName}"
	 vehicle['vehicle_company_SID'] = "${it.CompanySID}"
	 vehicle['vehicle_fuel_draw_capacity'] = "${it.FuelDrawCapacity}"
	 vehicle['vehicle_gross_vehicle_weight'] = "${it.GrossVehicleWeight}"
	 vehicle['vehicle_HP'] = "${it.HP}"
         vehicle['vehicle_HUT'] = "${it.HUT}"
         vehicle['vehicle_has_berth'] = "${it.HasBerth}"
         vehicle['vehicle_has_electronic_engine'] = "${it.HasElectronicEngine}"
         vehicle['vehicle_IFTA'] = "${it.IFTA}"
	 vehicle['vehicle_install_date'] = "${it.InstallDate}"
         vehicle['vehicle_license_plate'] = "${it.LicensePlate}"
         vehicle['vehicle_manufacture'] = "${it.Manufacture}"
	 vehicle['vehicle_manufacture_date'] = "${it.ManufactureDate}"
         vehicle['vehicle_modified_by'] = "${it.ModifiedBt}"
         vehicle['vehicle_modified_date'] = "${it.ModifiedDate}"
	 vehicle['vehicle_OBC_type'] = "${it.OBCType}"
         vehicle['vehicle_odometer'] = "${it.Odometer}"
         vehicle['vehicle_odometer_date'] = "${it.OdometerDate}"
         vehicle['vehicle_organization_id'] = "${it.OrganizationID}"
         vehicle['vehicle_organization_name'] = "${it.OrganizationName}"
	 vehicle['vehicle_organization_sid'] = "${it.OrganizationSID}"
         vehicle['vehicle_owner_operator'] = "${it.OwnerOperator}"
         vehicle['vehicle_power_axles'] = "${it.PowerAxles}"
         vehicle['vehicle_SID'] = "${it.SID}"
         vehicle['vehicle_status'] = "${it.Status}"
	 vehicle['vehicle_straight_truck'] = "${it.StraightTruck}"
         vehicle['vehicle_TGT_number'] = "${it.TGTNumber}"
         vehicle['vehicle_type'] = "${it.Type}"
         vehicle['vehicle_VIN'] = "${it.VIN}"
         vehicle['vehicle_vehicle_name'] = "${it.VehicleName}"
	 vehicle['vehicle_year'] = "${it.Year}"
         vehicleinfo.push(vehicle)
	// println vehicleinfo
         }
	 xmlPayload.ArrayOfFaultCode.FaultCode.each {
         def faultcode = new LinkedHashMap()
         faultcode['faultcode_assetID'] = "${it.AssetID}"
	 faultcode['faultcode_asset_type'] = "${it.AssetType}"
	 faultcode['faultcode_FMI'] = "${it.FMI}"
	 faultcode['faultcode_fault_date_time'] = "${it.FaultDateTime}"
	 faultcode['faultcode_fault_description'] = "${it.FaultDescription}"
	 faultcode['faultcode_fault_duration'] = "${it.FaultDuration}"
         faultcode['faultcode_gps_speed'] = "${it.GPSSpeed}"
         faultcode['faultcode_heading'] = "${it.Heading}"
         faultcode['faultcode_J1708J1587MID'] = "${it.J1708J1587MID}"
         faultcode['faultcode_J1708J1587PID'] = "${it.J1708J1587PID}"
	 faultcode['faultcode_J1708J1587SID'] = "${it.J1708J1587SID}"
         faultcode['faultcode_J1939PGN'] = "${it.J1939PGN}"
         faultcode['faultcode_J1939SA'] = "${it.J1939SA}"
         faultcode['faultcode_J1939SPN'] = "${it.J1939SPN}"
         faultcode['faultcode_lamp_amber'] = "${it.LampAmber}"
	 faultcode['faultcode_lamp_malfunction'] = "${it.LampMalfunction}"
         faultcode['faultcode_lamp_protect'] = "${it.LampProtect}"
         faultcode['faultcode_lamp_red'] = "${it.LampRed}"
         faultcode['faultcode_latitude'] = "${it.Latitude}"
         faultcode['faultcode_location'] = "${it.Location}"
	 faultcode['faultcode_longitude'] = "${it.Longitude}"
         faultcode['faultcode_occurrence_count'] = "${it.OccurrenceCount}"
         faultcode['faultcode_organization_id'] = "${it.OrganizationID}"
         faultcode['faultcode_organization_name'] = "${it.OrganizationName}"
         faultcode['faultcode_road_speed'] = "${it.RoadSpeed}"
  	 faultcode['faultcode_SID'] = "${it.SID}"
	 faultcode['faultcode_VIN'] = "${it.VIN}"
	 faultcode['load_dts'] = "${dateString}"
	 faultcodeinfo.push(faultcode)
	// println faultcodeinfo
	}	

for(int i=0; i<vehicleinfo.size(); i++){
        if(vehiclestatusinfo[i] == null){
        def ifnull = new LinkedHashMap()
          ifnull['vehiclestatus_direction'] = ""
          ifnull['vehiclestatus_driverId'] = ""
          ifnull['vehiclestatus_driver_name'] = ""
          ifnull['vehiclestatus_driver_organization_name'] = ""
          ifnull['vehiclestatus_driver_organization_SID'] = ""
          ifnull['vehiclestatus_driverSID'] = ""
          ifnull['vehiclestatus_engine_hours'] = ""
          ifnull['vehiclestatus_engine_speed'] = ""
          ifnull['vehiclestatus_heading'] = ""
          ifnull['vehiclestatus_ignition_status'] = ""
          ifnull['vehiclestatus_last_update'] = ""
          ifnull['vehiclestatus_motion_state'] = ""
          ifnull['vehiclestatus_odometer'] = ""
          ifnull['vehiclestatus_site_latitude'] = ""
          ifnull['vehiclestatus_site_longitude'] = ""
          ifnull['vehiclestatus_site_SID'] = ""
          ifnull['vehiclestatus_speed'] = ""
          ifnull['vehiclestatus_vehicle_latitude'] = ""
          ifnull['vehiclestatus_vehicle_location'] = ""
          ifnull['vehiclestatus_vehicle_longitude'] = ""
          ifnull['vehiclestatus_vehicle_name'] = ""
          ifnull['vehiclestatus_vehicle_organization_name'] = ""
          ifnull['vehiclestatus_vehicle_organization_SID'] = ""
          ifnull['vehiclestatus_vehicle_SID'] = ""
	  vehiclestatusinfo[i] = ifnull
       }
       if(faultcodeinfo[i] == null) {
       def ifnull = new LinkedHashMap()
          ifnull['faultcode_assetID'] = ""
          ifnull['faultcode_asset_type'] = ""
          ifnull['faultcode_FMI'] = ""
          ifnull['faultcode_fault_date_time'] = ""
          ifnull['faultcode_fault_description'] = ""
          ifnull['faultcode_fault_duration'] = ""
          ifnull['faultcode_gps_speed'] = ""
          ifnull['faultcode_heading'] = ""
          ifnull['faultcode_J1708J1587MID'] = ""
          ifnull['faultcode_J1708J1587PID'] = ""
          ifnull['faultcode_J1708J1587SID'] = ""
          ifnull['faultcode_J1939PGN'] = ""
          ifnull['faultcode_J1939SA'] = ""
          ifnull['faultcode_J1939SPN'] = ""
          ifnull['faultcode_lamp_amber'] = ""
          ifnull['faultcode_lamp_malfunction'] = ""
          ifnull['faultcode_lamp_protect'] = ""
          ifnull['faultcode_lamp_red'] = ""
          ifnull['faultcode_latitude'] = ""
          ifnull['faultcode_location'] = ""
          ifnull['faultcode_longitude'] = ""
          ifnull['faultcode_occurrence_count'] = ""
          ifnull['faultcode_organization_id'] = ""
          ifnull['faultcode_organization_name'] = ""
          ifnull['faultcode_road_speed'] = ""
          ifnull['faultcode_SID'] = ""
          ifnull['faultcode_VIN'] = ""
	  ifnull['load_dts'] = "${dateString}"
	  faultcodeinfo[i] = ifnull
       } 
		tmpassetList << vehiclestatusinfo[i]  + vehicleinfo[i] + faultcodeinfo[i] 
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
