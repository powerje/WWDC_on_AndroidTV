#!/usr/bin/env python

import json

with open('original_videos.json') as data_file:    
    data = json.load(data_file)["sessions"]
    output = {}
    for video in data:
        print video["year"]
        if 2016 == video["year"]:
            key = video["track"]
            if key == "":
                key = "misc"
            current_list = output.get(key, [])
            current_list.append(video)
            output[key] = current_list
    android_out = []
    keys = sorted(output)
    for x in keys:
        android_out.append({"name": x,
                            "videos": output[x]})
    with open('android_videos.json', 'w') as outfile:
            json.dump(android_out, outfile)
