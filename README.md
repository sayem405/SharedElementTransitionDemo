# SharedElementTransitionDemo
This is a handson practice of recyclerview to viewpager smooth transition. It is exactly same as the [demo](https://github.com/google/android-transition-examples/tree/master/GridToPager), but it is written in Kotlin. 

This Android project accompanies the [Continuous Shared Element Transitions: RecyclerView to ViewPager](https://goo.gl/Txqtds ) article. 

The code here provides the implementation for a specific transition between Android Fragments. 
It demonstrates how to implement a transition from an image in a `RecyclerView` into an image in a 
`ViewPager` and back, using ‘Shared Elements’ to determine which views participate in the transition 
and how. It also handles the tricky case of transitioning back to the grid after paging to an item 
that was previously offscreen.

![Demo](doc/transition.gif "Grid to Pager demo.")

## Licence
[Apache License Version 2.0](https://www.apache.org/licenses/LICENSE-2.0.txt)
