package com.example.listview

import java.io.Serializable

class Token : Serializable
{
    var resID:Int?=null;
    var theme:String?=null;
    var name:String?=null;
    var description:String?=null;
    var sources:String?=null;
    var message:String?=null;
    var number:Int?=null;

    constructor(name:String, theme:String, resID:Int, description:String, message:String, sources:String, number:Int)
    {
        this.name = name;
        this.theme = theme;
        this.resID = resID;
        this.description = description;
        this.message = message;
        this.sources = sources;
        this.number = number;
    }
}