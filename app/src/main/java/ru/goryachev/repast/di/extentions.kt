package ru.goryachev.repast.di

import toothpick.Scope
import toothpick.Toothpick
import toothpick.config.Module

fun Scope.inject(any: Any) = Toothpick.inject(any, this)

fun Scope.module(func: Module.() -> Unit): Scope {
    installModules(ru.goryachev.repast.di.module { func(this) })
    return this
}

fun Scope.moduleFlow(func: Module.() -> Unit): Scope {
    installModules(ru.goryachev.repast.di.flowModule { func(this) })
    return this
}