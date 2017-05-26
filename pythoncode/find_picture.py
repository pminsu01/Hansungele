#-*- coding: utf-8 -*-

from openalpr import Alpr
import sys
import time
import send_data
reload(sys)

sys.setdefaultencoding('utf-8')
#
car_String= ''
car_number= ''
car_info =''

def find_images(Filename) :

    while(True):
        alpr = Alpr("kr", "/etc/openalpr/openalpr.conf", "/usr/share/openalpr/runtime_data")
        if not alpr.is_loaded():
            print("Error loading OpenALPR")
            sys.exit(1)

        alpr.set_top_n(20)
        alpr.set_default_region("md")

        results = alpr.recognize_file(Filename)

        i = 0

        for plate in results['results']:
            i += 1
            print("Plate #%d" % i)
            print("   %12s %12s" % ("Plate", "Confidence"))
            for candidate , a in plate['candidates']:
                prefix = "-"
                if candidate['matches_template']:
                    prefix = "*"

                print("  %s %12s%12f" % (prefix, candidate['plate'], candidate['confidence']))

                    car_full_info[a] = candidate['plate'].encode('UTF-8')


            car_String = car_full_info[:5]
            print(type(car_String))
            car_number = car_full_info[5:]
            print (car_String,car_number)
            #send_data.send_data(filename,car_full_info,car_String,car_number)

        # Call when completely done to release me   mory
        alpr.unload()
        sec =10
        time.sleep(sec)




if __name__ =="__main__":

    find_images(filename)