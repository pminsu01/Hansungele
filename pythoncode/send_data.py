#-*- coding: utf-8 -*-
import boto3
import sys
import requests


car_where ='b1a3' # 고정된 값
filename='images.jpg' # filename 이미지저장
txt_filename ="car.txt"

def car_txt(txt_filename):

        car_String= open(txt_filename,'r')
        car_info = car_String.readline()[:-1]
        car_info = car_info+".jpg"
        car_String = car_info[:5]
        car_number = car_info[5:9]

        return car_info, car_String, car_number


def send_image(filename,key):
        s3 = boto3.resource('s3')
        bucket_name = 'hansungelepic'
        bucket = s3.Bucket(bucket_name)
        bucket.upload_file(filename, key, ExtraArgs={'ContentType': 'image/jpeg'})
        url = "https://s3.ap-northeast-2.amazonaws.com/"+bucket_name+"/"+key

        return url


def send_data(txt_filename):

        car_info, car_String, car_number = car_txt(txt_filename)  # car_txt return 값 String, number값 저장
        car_picture_url = send_image(filename,car_info) #send_image return 값 url 저장


        print "url은 이거다",car_picture_url

        data = {'car_String': car_String,
                'car_number': car_number,
                'car_where': car_where,
                'car_picture_url': car_picture_url}

        path ="http://ec2-52-79-216-228.ap-northeast-2.compute.amazonaws.com/rasp_server.php"

        resq = requests.post(path, data=data)

        print resq.status_code


if __name__ =="__main__":

        send_data(txt_filename)
