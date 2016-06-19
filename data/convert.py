#!/usr/bin/env python

import json

with open('original_videos.json') as data_file:    
    data = json.load(data_file)["sessions"]
    output = {}
    for video in data:
        if video["download_hd"] and ".mov" not in video["download_hd"]: 
            year = video["year"]
            key = "{0} - {1}".format(year, video["track"])
            current_list = output.get(key, [])
            current_list.append(video)
            output[key] = current_list
    android_out = []
    keys = reversed(sorted(output))
    for x in keys:
        android_out.append({"name": x,
                            "videos": output[x]})
    with open('android_videos.json', 'w') as outfile:
            json.dump(android_out, outfile)
