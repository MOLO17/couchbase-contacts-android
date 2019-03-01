package com.molo17.couchbasedemo.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.molo17.couchbasedemo.Contact
import com.molo17.couchbasedemo.R
import kotlinx.android.synthetic.main.activity_contact.view.*

/**
 * Created by Matteo Sist on 28/02/2019.
 */
class ContactsListRecyclerViewAdapter :
    RecyclerView.Adapter<ContactsListRecyclerViewAdapter.ViewHolder>() {

    var contacts: List<Contact> = emptyList()
    var onContactClickListener: ((contactId: String) -> Unit)? = null
    var onContactLongClickListener: ((contactId: String) -> Unit)? = null

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_contact, parent, false)
        return ViewHolder(view).apply {
            itemView.setOnClickListener { onContactClickListener?.invoke(contacts[adapterPosition].id) }
            itemView.setOnLongClickListener { onContactLongClickListener?.invoke(contacts[adapterPosition].id); true }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = contacts[position]
        holder.nameSurname.text = makeNameSurname(item)
        holder.phoneNumber.text = makePhoneNumber(item)
    }

    override fun getItemCount(): Int = contacts.size

    override fun getItemId(position: Int): Long = contacts[position].id.hashCode().toLong()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameSurname: TextView = view.nameSurnameTextView
        val phoneNumber: TextView = view.phoneNumberTextView
    }

    private fun makeNameSurname(contact: Contact): String {
        return listOfNotNull(contact.name, contact.surname).run {
            if (isEmpty()) "No name"
            else joinToString(" ")
        }
    }

    private fun makePhoneNumber(contact: Contact): String {
        return contact.phoneNumber?.takeIf(String::isNotBlank) ?: "No phone number"
    }
}