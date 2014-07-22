/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 .
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */
package org.jboss.windup.exec.config;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Class for storing global information needed for migration. Like dir of AS7, AS5, and profiles
 * <p/>
 * With regard of possibility to migrate from other vendors' AS,
 * split to AS 7 config class, and then have 1 class per server (AS 5, WebLogic, ...).
 */
@XmlRootElement(name="global")
public class CoreConfiguration {

    private Set<String> appPaths = new HashSet();

    private boolean skipValidation = false;
    
    private boolean dryRun = false;

    private boolean isTestRun = false;
    
    // This is rather for test purposes. If null, all are used.
    private List<String> onlyRulesets = new LinkedList();
    
    private String reportDir = "MigrationReport";
    
    private String externalMigratorsDir;

    // User vars - accessible in EL's and Groovy scripts. WINDUP-132
    private Map<String, String> userVars = new HashMap();

    
    
    //<editor-fold defaultstate="collapsed" desc="get/set">
    public Set<String> getDeploymentsPaths() { return appPaths; }
    public void addDeploymentPath(String deplPath) { this.appPaths.add( deplPath ); }
    
    public boolean isSkipValidation() { return skipValidation; }
    public void setSkipValidation(boolean skipValidation) { this.skipValidation = skipValidation; }
    
    public boolean isDryRun() { return dryRun; }
    public void setDryRun( boolean dryRun ) { this.dryRun = dryRun; }
    
    public boolean isTestRun() { return isTestRun; }
    public void setTestRun( boolean isTestRun ) { this.isTestRun = isTestRun; }
    
    public String getReportDir() { return reportDir; }
    public void setReportDir( String reportDir ) { this.reportDir = reportDir; }

    public String getExternalMigratorsDir() { return externalMigratorsDir; }
    public void setExternalMigratorsDir( String externalMigratorsDir ) { this.externalMigratorsDir = externalMigratorsDir; }

    public List<String> getOnlyMigrators() { return onlyRulesets; }
    public List<String> addOnlyMigrator( String name ) { onlyRulesets.add(name); return onlyRulesets; }

    // WINDUP-132
    public Map<String, String> getUserVars() { return userVars; }
    public String getUserVar( String name) { return userVars.get(name); }
    public void setUserVar( String name, String val ) { this.userVars.put( name, val ); }
    //</editor-fold>

}// class
