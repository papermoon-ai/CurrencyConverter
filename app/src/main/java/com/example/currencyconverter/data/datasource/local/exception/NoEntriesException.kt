package com.example.currencyconverter.data.datasource.local.exception

import java.lang.Exception

private const val NO_ENTRIES_MESSAGE = "No entries in database"

class NoEntriesException() : Exception(NO_ENTRIES_MESSAGE)