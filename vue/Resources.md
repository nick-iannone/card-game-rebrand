//  HEARTBEATING POSSIBLE SOLUTION

export default{
        hb : null,
        testWs(id){  // Recursively detect network disconnection
        let WebSocketId = id
        let _self = this

        _self.hb = setInterval(function(){
                console.log('try ididdi = ' + WebSocketId)
                // console.log(_self.bt_kernels)
                _self.bt_kernels[WebSocketId.toString()].ws.send("^_^")
                // console.log ("I'm still alive, so good. @ ^ _ ^ @")
                if(_self.bt_kernels[WebSocketId.toString()].ws.readyState == 2 || _self.bt_kernels[WebSocketId.toString()].ws.readyState == 3){
                    // console.log ("I ’m so cute, but dead @ T_T @")
                    alert("websocket has been disconnected and is reconnecting ...")
                    clearInterval(_self.hb)
                    let i = 1
                    let myVar = setInterval(function(){
                        console.log('catch ididdi = ' + WebSocketId)
                        console.log(_self.bt_kernels)
                        if(_self.bt_kernels[WebSocketId.toString()].ws.readyState == 2 || _self.bt_kernels[id.toString()].ws.readyState == 3){

                            _self.bt_kernels[WebSocketId.toString()].ws = new WebSocket(_self.bt_kernels[id.toString()].ws.url)
                            // console.log ("Try the first" + i + "second reconnect")
                            if(i == 10){
                                // console.log ("Can't save any more, give up reconnecting")
                                alert("websocket failed to reconnect, please refresh the page")
                                clearInterval(myVar)
                            }
                            i++
                        }
                        if(_self.bt_kernels[WebSocketId.toString()].ws.readyState == 0){
                            // console.log ("Trying to reconnect ...")
                        }else if(_self.bt_kernels[WebSocketId.toString()].ws.readyState == 1){
                            // console.log ("I am alive again. Y ^ _ ^ Y")
                            clearInterval(myVar)
                                _self.testWs(WebSocketId)
                            }
                    },10*1000)
                }
        }, 10*1000)
    },
}

https://www.programmersought.com/article/42323682553/




