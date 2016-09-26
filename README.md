STATE
Only state is maintained through SessionAttrUtil. Actions are stateless. Each action creates and configures a new service instance.

PAGE REFRESHES
Every action concludes with a redirect (HTTP 302) - to an external URL, an internal JSP or maybe another action. So, page refreshes should not have side-effects besides reloading the page.

REST XML RESPONSE
Add dependency: jersey-media-jaxb

REST JSON RESPONSE


LINKS
https://www.linkedin.com/developer/apps
https://developers.google.com/identity/protocols/OpenIDConnect

 