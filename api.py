"""
import wolframalpha
import tungsten

def create_question():
    text1="apple"
    text2="microsoft"
    #textToSend = text1 + " vs " + text2
    textToSend = 'who are you'
    print textToSend
    app_id = "WU9PU5-G272R6XVTQ"
    client = tungsten.Tungsten(app_id)
    res = client.query(textToSend)
    for pod in res.pods:
        print pod.scanner
        print pod.id
        print pod.title
        print "end"


create_question()
"""

import wap
def create_question():
    server = 'http://api.wolframalpha.com/v1/query.jsp'
    appid = 'WU9PU5-G272R6XVTQ'
    input = 'apple vs microsoft'
    scantimeout = '3.0'
    podtimeout = '4.0'
    formattimeout = '8.0'
    async = 'False'
    waeo = wap.WolframAlphaEngine(appid, server)

    waeo.ScanTimeout = scantimeout
    waeo.PodTimeout = podtimeout
    waeo.FormatTimeout = formattimeout
    waeo.Async = async

    query = waeo.CreateQuery(input)

    waeq = wap.WolframAlphaQuery(input, appid)

    waeq.ScanTimeout = scantimeout
    waeq.PodTimeout = podtimeout
    waeq.FormatTimeout = formattimeout
    waeq.Async = async
    waeq.ToURL()
    '''waeq.AddPodTitle('')
    waeq.AddPodIndex('')
    waeq.AddPodScanner('')
    waeq.AddPodState('')
    waeq.AddAssumption('')'''

    query = waeq.Query

    #print '***wapex output***', '\n', 'server=' + server + '\n', query

    result = waeo.PerformQuery(query)

    waeqr = wap.WolframAlphaQueryResult(result)
    datatypes = waeqr.DataTypes()
    print '\n','datatypes**', type(datatypes), 'datatypes=', datatypes
    pods = waeqr.Pods()
    for pod in pods:
        waep = wap.Pod(pod)
        title = waep.Title()
        #print '\n','title: ***', type(title), 'title=', title
        print '\n','title:',title[0]
        scanner = waep.Scanner()
        #print '\n','scanner: ***', type(scanner), 'scanner=', scanner
        position = waep.Position()
        #print '\n','position: ***', type(position), 'position=', position
        asynchurl = waep.AsynchURL()
        #print '\n', 'aysnc: ***',type(asynchurl), 'asynchurl=', asynchurl
        subpods = waep.Subpods()
        for subpod in subpods:
            #print 'inside subpod'
            waesp = wap.Subpod(subpod)
            #print type(waesp)
            title = waesp.Title()
            #print '\n','title: ***', type(title), 'subpod title=', title

            plaintext = waesp.Plaintext()

            print '\n','plaintext=', plaintext

            img = waesp.Img()
            print '\n','img=',img[0][0][1]

        #print 'end of subpod'

create_question()
