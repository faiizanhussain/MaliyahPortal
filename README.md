# Maliyah Portal

This repository contains the source of **Maliyah Portal**, an Oracle ADF application.

## Project Modules

The JDeveloper workspace `MaliyahPortalApplication.jws` includes three project modules as referenced in the workspace file:

```
<list n="listOfChildren">
   <hash><url n="URL" path="MaliyahPortal/MaliyahPortal.jpr"/></hash>
   <hash><url n="URL" path="Model/Model.jpr"/></hash>
   <hash><url n="URL" path="ViewController/ViewController.jpr"/></hash>
</list>
```

- **Model** – ADF Business Components project (`Model/Model.jpr`).
- **ViewController** – Web user interface built with ADF Faces (`ViewController/ViewController.jpr`).
- **MaliyahPortal** – Library project containing reusable assets.

## Required Tools

- **Oracle JDeveloper 12c (12.2.1.4.0)** – Project files reference this version of the IDE and libraries.
- **Oracle ADF Runtime 12.2.1.4.0** – Required on the target WebLogic Server for deployment.

## Configuring WebCenter Content Connection

Managed beans under `ViewController/src/view/managedBean` use RIDC to interact with WebCenter Content. Connection details are hard coded. Example from `UploadDocuments.java`:

```
IdcClientManager manager = new IdcClientManager();
IdcClient idcClient = manager.createClient("idc://10.161.9.17:4444");
IdcContext userContext = new IdcContext("weblogic");
```

Update the host, port, username and password in these classes to match your WCC environment. Several beans contain similar code (`UpdateAndDeleteNew.java`, `Library.java`, `DocumentLib.java`, etc.). Paths such as `/Enterprise Libraries/Maliyah_Portal/MaliyahDocuments` may also need updates for your repository structure.

## Running the Portal

1. Open `MaliyahPortalApplication.jws` in JDeveloper 12c.
2. Configure the database connection defined in `.adf/META-INF/connections.xml` if required.
3. Start the integrated WebLogic Server (`DefaultServer`).
4. Right click `test.jspx` (under `ViewController/public_html`) and choose **Run** to launch the portal.

## Deployment

To create a WAR for deployment on a standalone WebLogic Server:

1. In JDeveloper, choose **Application** → **Deploy** → `MaliyahPortalApplication_Project1_MaliyahPortalApplication`.
2. Deploy to an EAR/WAR file or directly to a target server that has Oracle ADF runtime installed.

