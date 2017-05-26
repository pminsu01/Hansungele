#-*- coding: utf-8 -*-

from openalpr import Alpr
import sys
import time
import send_data
reload(sys)

sys.setdefaultencoding('utf-8')




def find_images(Filename,car_where) :

    #while(True):
        alpr = Alpr("kr", "/etc/openalpr/openalpr.conf", "/usr/local/share/openalpr/runtime_data")
        print("find_image arrive")
        if not alpr.is_loaded():
            print("Error loading OpenALPR")
            sys.exit(1)

        alpr.set_top_n(20)
        alpr.set_default_region("md")

        results = alpr.recognize_file(Filename)

        i = 0
        car_info =[]
	if(results['results'] ==[]) : return

        for plate in results['results']:
            i += 1
            print("Plate #%d" % i)
            print("   %12s %12s" % ("Plate", "Confidence"))
            #print plate
            for candidate in plate['candidates']: #plate의 갯수대로
                #print"plate",plate['candidates']
                prefix = "-"
                if candidate['matches_template']:
                    prefix = "*"
                    print("  %s %12s%12f" % (prefix, candidate['plate'], candidate['confidence']))
                    #max =candidate['confidence']
                    car_info.append(candidate['plate'].encode("UTF-8"))
                    print "car_info", str(car_info[0])



        #print(accuracy)
        #print(car_full_info)
        car_1st_info = str(car_info[0])
        car_string =car_1st_info[:5]
        car_number = car_1st_info[5:]
        print car_string, car_number,car_where
        send_data.send_server(Filename,car_1st_info,car_string,car_number,car_where)

        # Call when completely done to release me   mory
        alpr.unload()
        #sec =10
        #time.sleep(sec)

