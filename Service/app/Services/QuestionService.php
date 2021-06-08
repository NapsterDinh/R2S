<?php
namespace App\Services;

use App\Models\Question;
use Illuminate\Support\Facades\DB;

class QuestionService {
    public function __construct() {

    }

    public function showQuestion() {
        $question = DB::table('Question')->select(
            'QuestionID',
            'Topic.TopicID',
            'QuestionContent',
            'Topic.TopicName',
        )->where('IsDeleted','=',0)
        ->join('Topic','Topic.TopicID','=','Question.TopicID')->get();

        return json_encode($question);
    }

    public function insertQuestion($data) {
        $question = Question::create([
            'QuestionID' => rand(1, 1000) ,
            'TopicID' => $data['TopicID'],
            'QuestionContent' => $data['QuestionContent'],
            'IsDeleted' => 0,
        ]);

        return json_encode([
            'result' => $question
        ]);
    }

    public function deleteQuestion($data) {
        $question = Question::where(
            'QuestionID', '=', $data['QuestionID'
        ])->update(['IsDeleted' => 1]);

        return json_encode([
            'result' => $question
        ]);
    }

    public function updateQuestion($data) {
        $question =  DB::table('Question')
    
        ->where(
            'QuestionID', '=', $data['QuestionID']
        )->update(['QuestionContent' => $data['QuestionContent']]);

        return json_encode([
            'result' => $question
        ]);
    }

    public function showTopicName($data) {
        $question =  DB::table('Question')
        ->join('Topic', 'Question.TopicID', '=', 'Topic.TopicID')
        ->where('Topic.TopicName', '=', $data['TopicName'])
        ->get();

        return json_encode($question);
    }

    public function searchQuestion($data) {
        $question =  DB::table('Question')->select(
            'QuestionID',
            'Topic.TopicID',
            'QuestionContent',
            'Topic.TopicName',
        )
            
        ->join('Topic', 'Question.TopicID', '=', 'Topic.TopicID')
        ->where('Topic.TopicName', '=', $data['TopicName'])
        ->where('IsDeleted','=',0)
        ->get();

        return json_encode($question);
    }


}