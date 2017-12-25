package com.mounacheikhna.algorithms.graphs

import java.util.*

typealias Nodes = List<Node>

class Node(val n: Int) {
    var index = -1
    var lowLink = -1
    var inStack = false

    override fun toString() = n.toString()
}

class DirectedGraph(val vs: Nodes, val edges: Map<Node, Nodes>)

fun tarjanScc(g: DirectedGraph): List<Nodes> {

    val sccs = mutableListOf<Nodes>()
    var index = 0
    val s = Stack<Node>()

    fun strongConnect(v: Node) {
        v.index = index
        v.lowLink = index
        index++
        s.push(v)
        v.inStack = true

        for (w in g.edges[v]!!) {
            if (w.index < 0) {
                strongConnect(w)
                v.lowLink = minOf(v.lowLink, w.lowLink)
            } else if (w.inStack) {
                v.lowLink = minOf(v.lowLink, w.index)
            }
        }


        if (v.lowLink == v.index) { // on root of scc
            val scc = mutableListOf<Node>()
            do {
                val w = s.pop()
                w.inStack = false
                scc.add(w)
            } while (w != v)
            sccs.add(scc)
        }
    }

    for (v in g.vs) if (v.index < 0) strongConnect(v)
    return sccs
}

fun main(args: Array<String>) {
    val vs = (0..7).map { Node(it) }
    val es = mapOf(
            vs[0] to listOf(vs[1]),
            vs[2] to listOf(vs[0]),
            vs[5] to listOf(vs[2], vs[6]),
            vs[6] to listOf(vs[5]),
            vs[1] to listOf(vs[2]),
            vs[3] to listOf(vs[1], vs[2], vs[4]),
            vs[4] to listOf(vs[5], vs[3]),
            vs[7] to listOf(vs[4], vs[7], vs[6])
    )
    val g = DirectedGraph(vs, es)
    val sccs = tarjanScc(g)
    println(sccs.joinToString("\n"))
}
