<?php

namespace App\Http\Controllers\Question;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Services\QuestionService;

class QuestionController extends Controller
{
    public function __construct(QuestionService $questionService) {
        $this->questionService = $questionService;
    }

    public function showQuestion(Request $request) {
        return $this->questionService->showQuestion();
    }

    public function insertQuestion(Request $request) {
        $data = $request->all();
        return $this->questionService->insertQuestion($data);
    }

    public function deleteQuestion(Request $request){
        $data=$request->all();
        return $this->questionService->deleteQuestion($data);
    }

    public function showTopicName(Request $request){
        $data=$request->all();
        return $this->questionService->showTopicName($data);
    }

    public function searchQuestion(Request $request){
        $data=$request->all();
        return $this->questionService->searchQuestion($data);
    }

    public function updateQuestion(Request $request){
        $data=$request->all();
        return $this->questionService->updateQuestion($data);
    }
}
