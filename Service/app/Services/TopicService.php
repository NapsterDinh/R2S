<?php
namespace App\Services;

use App\Models\Topic;
use Illuminate\Support\Facades\DB;

class TopicService {
    public function __construct() {

    }

    public function showTopic() {
        $question = DB::table('Topic')->select(
            'TopicID',
            'TopicName',
        )->get();

        return json_encode($question);
    }   
}