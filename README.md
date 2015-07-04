Used with snmpd pass to provide table data (using java)
====================

edit /etc/snmp/snmpd.conf (add the line below) 
<pre>
pass .1.3.6.1.4.1.8888.255 /usr/bin/java -jar /path/to/TableProviderForSnmpd.jar
</pre>
