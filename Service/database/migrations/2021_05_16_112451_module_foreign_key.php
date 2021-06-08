<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class ModuleForeignKey extends Migration
{
      /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::table('Module', function (Blueprint $table) {
            $table->foreign('FeedbackID')->references('FeedbackID')->on('Feedback');
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
        Schema::table('Module', function (Blueprint $table) {
            $table->dropForeign(['FeedbackID', 'AdminID']);
        });
    }
}
