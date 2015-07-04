A simple java sample app used with snmpd pass to provide table data 
====================

edit /etc/snmp/snmpd.conf (add the line below) 
<pre>
pass_persist .1.3.6.1.4.1.8888.255 /usr/bin/java -jar /path/to/TableProviderForSnmpd.jar
</pre>
