update usr set password = if(username!='admin',MD5(password),password);