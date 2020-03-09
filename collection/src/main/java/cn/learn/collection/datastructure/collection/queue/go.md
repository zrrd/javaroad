#### java Queue remove/poll, add/offer, element/peek
offer，add区别：
> 一些队列有大小限制，因此如果想在一个满的队列中加入一个新项，多出的项就会被拒绝。
> 这时新的 offer 方法就可以起作用了。它不是对调用 add() 方法抛出一个 unchecked 异常，而只是得到由 offer() 返回的 false。 
 
 poll，remove区别：
 > remove() 和 poll() 方法都是从队列中删除第一个元素。remove() 的行为与 Collection 接口的版本相似，
 > 但是新的 poll() 方法在用空集合调用时不是抛出异常，只是返回 null。因此新的方法更适合容易出现异常条件的情况。
   
peek，element区别：
> element() 和 peek() 用于在队列的头部查询元素。与 remove() 方法类似，在队列为空时， element() 抛出一个异常，而 peek() 返回 null


对于 阻塞队列BlockingQueue
put(e)  offer(e, time, unit)
> put 队列慢会阻塞当前线程  offer超时返回

take()	poll(time, unit)
> take 队列为空会阻塞当前线程 poll超时返回  