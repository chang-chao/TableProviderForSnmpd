A simple java sample app used with snmpd pass to provide table data 
====================

edit /etc/snmp/snmpd.conf (add the line below) 
<pre>
pass .1.3.6.1.4.1.8888.255 /usr/bin/java -jar /path/to/TableProviderForSnmpd.jar
</pre>

remember to disable selinux.

<pre>

[root@localhost ~]# snmpwalk -v2c -c public localhost .1.3.6.1.4.1.8888.255.1
SNMPv2-SMI::enterprises.8888.255.1.1.1 = STRING: "2"
SNMPv2-SMI::enterprises.8888.255.1.1.2 = STRING: "*"
SNMPv2-SMI::enterprises.8888.255.1.2.1 = STRING: "OK"
SNMPv2-SMI::enterprises.8888.255.1.2.2 = STRING: "NG"

[root@localhost ~]# grep foo /etc/snmp/snmpd.conf
com2sec foobarSec       default       foobar
group   foobarGroup    v2c          foobarSec
view     foobarview    included   .1.3.6.1.4.1.8888.255.1
access  foobarGroup    ""      any       noauth    exact  foobarview  none none

[root@localhost ~]# snmpwalk -v2c -c foobar localhost .1.3.6.1.4.1.8888.255.1
SNMPv2-SMI::enterprises.8888.255.1.1.1 = STRING: "2"
SNMPv2-SMI::enterprises.8888.255.1.1.2 = STRING: "*"
SNMPv2-SMI::enterprises.8888.255.1.2.1 = STRING: "OK"
SNMPv2-SMI::enterprises.8888.255.1.2.2 = STRING: "NG"
SNMPv2-SMI::enterprises.8888.255.1.2.2 = No more variables left in this MIB View (It is past the end of the MIB tree)

</pre>


