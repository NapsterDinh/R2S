<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class FeedbackForeignKey extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::table('Feedback', function (Blueprint $table) {
            $table->foreign('TypeFeedbackID')->references('TypeID')->on('TypeFeedback');
            $table->foreign('AdminID')->references('UserName')->on('Admin');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::table('Feedback', function (Blueprint $table) {
            $table->dropForeign(['TypeFeedbackID','AdminID']);
        });
    }
}
