package edu.se.ustc.item;

import java.util.ArrayList;
import java.util.List;

public class BusRouteInfo {

    Integer id;
    String stationName;// the name of bus station
    String stationSeries = "-1";// the series of a station
    String busLicence = "-1";// a bus's licence
    String enterStationTime = "-1";// enter the station's time
    String attribute;// current time

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getAttribute() {
        return attribute;
    }
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
    
	public String getEnterStationTime() {
		return enterStationTime;
	}
	public void setEnterStationTime(String enterStationTime) {
		this.enterStationTime = enterStationTime;
	}

	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getStationSeries() {
		return stationSeries;
	}
	public void setStationSeries(String stationSeries) {
		this.stationSeries = stationSeries;
	}
	
	public String getBusLicence() {
		return busLicence;
	}
	public void setBusLicence(String busLicence) {
		this.busLicence = busLicence;
	}

	@Override
	//the string which will be printed each line
	public String toString() {
		return "BusRouteInfo [stationName=" + stationName
				+ ", stationSeries=" + stationSeries + ", busLicence="
				+ busLicence + ", enterStationTime=" + enterStationTime + "]";
	}
	
//<moded-by-Pei 2015/04/20  the operation of businfo
    /**
     * get each station information with the format of String
     * @param str
     * @return
     */
	public List<String> getEachStationInfo(String str) {
        List<String> stringList = new ArrayList<String>();

        while (str != null && str.indexOf("<tr>") >= 0
                && str.indexOf("</tr>") >= 0) {
            // System.out.println(str.indexOf("<tr>"));
            String content = str.substring(str.indexOf("<tr>"),
                    str.indexOf("</tr>") + 5);
            // System.out.println(content);
            stringList.add(content);
            str = str.substring(str.indexOf("</tr>") + 5);
        }
        return stringList;
    }
	
    /**
     * get BusRouteInfo from String information
     * @param bsiString
     * @return
     */
   public  BusRouteInfo stringToBusRouteInfo(String bsiString) {
        if (bsiString.indexOf("<td>") < 0 || bsiString.indexOf("</td>") < 0) {
            return null;
        }
        BusRouteInfo bsi = new BusRouteInfo();
        String str = bsiString;
        String content = str.substring(str.indexOf("<td>"),
                str.indexOf("</td>"));
        if (content.indexOf("StandName") >= 0) {
            bsi.setStationName(content.substring(
                    content.indexOf("StandName") + 10, content.indexOf("\">")));
        }

        str = str.substring(str.indexOf("</td>") + 5);
        content = str.substring(str.indexOf("<td>") + 4, str.indexOf("</td>"));
        if (content != "") {
            bsi.setStationSeries(content);
        }

        str = str.substring(str.indexOf("</td>") + 5);
        content = str.substring(str.indexOf("<td>") + 4, str.indexOf("</td>"));
        if (content != "") {
            bsi.setBusLicence(content);
        }

        str = str.substring(str.indexOf("</td>") + 5);
        content = str.substring(str.indexOf("<td>") + 4, str.indexOf("</td>"));

        if (content != "") {

            bsi.setEnterStationTime(content);
        }
        return bsi;
    }
   
   /**
    * get BusRouteInfo List from String List
    * @param stringList
    * @return
    */
    public List<BusRouteInfo> getBusRouteInfoList(List<String> stringList) {
        List<BusRouteInfo> bsiList = new ArrayList<BusRouteInfo>();
        int size = 0;

        while (size < stringList.size()) {
            String bsiString = stringList.get(size);
            if (stringToBusRouteInfo(bsiString) != null)
                bsiList.add(stringToBusRouteInfo(bsiString));
            size++;
        }
        return bsiList;
        
        
    }

    
    /**
     * output BusStation List
     * @param bsiList
     */
   public  void printBusRouteInfo(List<BusRouteInfo> bsiList) {
        for (int i = 0; i < bsiList.size(); i++) {
            System.out.println(bsiList.get(i));
        }
   }	
}
//>